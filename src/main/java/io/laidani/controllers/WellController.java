package io.laidani.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.laidani.models.Field;
import io.laidani.models.Perforation;
import io.laidani.models.Perimeter;
import io.laidani.models.ReservoirType;
import io.laidani.models.Well;
import io.laidani.models.WellMapping;
import io.laidani.models.WellTest;
import io.laidani.models.WellType;
import io.laidani.repositories.IWellMappingDAO;
import io.laidani.services.IFieldService;
import io.laidani.services.IPerimeterService;
import io.laidani.services.IWellService;

@Repository
@RequestMapping(path = "/api/wells")
public class WellController implements WebMvcConfigurer {
	
	public static final String ALL_PAGE = "allWells";
	public static final String ADD_PAGE = "addWell";	
	public static final String EDIT_PAGE = "editWell";
	public static final String REDIRECT_ALL_WELLS ="redirect:/api/wells/all";
	
	@Autowired
	IWellService wellService;
	
	@Autowired
	IFieldService fieldService;
	
	@Autowired
	IPerimeterService perimeterService;
	
	@Autowired
	IWellMappingDAO wellMappingDAO;
	
	@GetMapping ("/hello")
	public String Hello() {
		return "HelloPage";
	}
	
	@GetMapping(value = "/all")
	public ModelAndView getAllWells(ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
	    /*
	     * TECH : Using PagingAndSortingRepository 
	     */
		int currentPage = 1;
		Page<Well> page = wellService.findAll(1, "wellName", "asc");
		int totalItems = page.getNumberOfElements();
		int totalPages = page.getTotalPages();
		
		map.put("field", "wellName");
		map.put("direction", "asc");
	
		map.put("currentPage", currentPage);
		map.put("totalItems", totalItems);
		map.put("totalPages", totalPages);
		map.put("wells", page.getContent());		
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(ALL_PAGE);
		return modelAndView;
	}
	
	@GetMapping(value = "/page/{pg}")
	public ModelAndView getAllWells(ModelAndView modelAndView, 
			                        @PathVariable(name = "pg") int pageNumber,
			                        @Param("field") String field,
			                        @Param("direction") String direction) {
		Map<String, Object> map = new HashMap<String, Object>();
	    /*
	     * TECH : Using PagingAndSortingRepository 
	     */
		int currentPage = pageNumber;
		Page<Well> page = wellService.findAll(currentPage-1, field, direction);
		int totalItems = page.getNumberOfElements();
		int totalPages = page.getTotalPages();
		map.put("field", field);
		map.put("direction", direction);
		map.put("currentPage", currentPage);
		map.put("totalItems", totalItems);
		map.put("totalPages", totalPages);
		map.put("wells", page.getContent());		
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(ALL_PAGE);
		return modelAndView;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView addWell(ModelAndView modelAndView) {
		Well well = new Well();
		well.setPerfos(new ArrayList<Perforation>());
		well.setTests(new ArrayList<WellTest>());
		well.setPerimeter(new Perimeter());
		Map<String, Object> map = new HashMap<String, Object>();
		
		// put Objects in a Map
		map.put("well", well);
		map.put("wellTypeEnum", WellType.values());
		map.put("reservoirTypeEnum", ReservoirType.values());
		
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(ADD_PAGE);
		return modelAndView;
	}

	@PostMapping(value = "/save")
	// TECH : use @ModelAttribute (name = "wellObj") @Valid Well well when Object name in the View != Object name in the Controller
	public ModelAndView saveWell(@Valid Well well, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes rattribs) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(bindingResult.hasErrors()) {		
			map.put("wellTypeEnum", WellType.values());
			map.put("reservoirTypeEnum", ReservoirType.values());
			
			map.put("message", "Insertion failed !");
			map.put("alertClass", "alert-warning");
			
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(ADD_PAGE);
		} else {
			wellService.saveWell(well);
						
			// put objects in a map
			Page<Well> page = wellService.findAll(1, "wellName", "asc");
			
			int totalItems = page.getNumberOfElements();
			int totalPages = page.getTotalPages();
			
			//map.put("wells", wellService.findAllWells());
			map.put("wells", page.getContent());
			map.put("message", "The Well <" + well.getWellName().toUpperCase() + "> has been saved successfully !");
			map.put("alertClass", "alert-success");
			map.put("currentPage", 1);
			map.put("totalItems", totalItems);
			map.put("totalPages", totalPages);
			map.put("field", "wellName");
			map.put("direction", "asc");
			// --
			modelAndView.addAllObjects(map);		
			modelAndView.setViewName(ALL_PAGE);
		}		
		return modelAndView;
	}
	
	@GetMapping(value = "/delete/{id}")
	public String deleteWell(@PathVariable(name = "id") int id, Model model, RedirectAttributes rattribs) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		rattribs.addFlashAttribute("alertClass", "alert-danger");
		rattribs.addFlashAttribute("message","The Well has been deleted successfully !");
		
		// delete well mapping
		int wmid = wellMappingDAO.findByWid(id).get().getUid();
		wellMappingDAO.deleteWm(wmid);
		/*
		 * TECH : RabbitMQ to be used here as well.
		 */
		
		// delete well
		wellService.deleteWell(id);	
		
		Page<Well> page = wellService.findAll(1, "WellName","asc");
		
		int totalItems = page.getNumberOfElements();
		int totalPages = page.getTotalPages();
		
		map.put("wells", wellService.findAllWells());
		map.put("currentPage", 1);
		map.put("totalItems", totalItems);
		map.put("totalPages", totalPages);
		map.put("field", "wellName");
		map.put("direction", "asc");
			
		model.addAllAttributes(map);
			
		return REDIRECT_ALL_WELLS;
	}
	
	
	@RequestMapping(value = "/edit/{id}")
	public ModelAndView editWell(@PathVariable(name = "id") int id, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
		Optional<Well> opWell = wellService.findWellById(id);
		
		if(opWell.isPresent()) {
			// put Objects in a Map
			Well well = opWell.get();
			map.put("well", well);
			map.put("field", well.getField());
			map.put("perimeter", well.getPerimeter());
			map.put("wellTypeEnum", WellType.values());
			map.put("reservoirTypeEnum", ReservoirType.values());		
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(EDIT_PAGE);
			}
 		return modelAndView;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView updateWell(@Valid Well well, BindingResult bindingResult, ModelAndView modelAndView) {	
				
		Map<String, Object> map = new HashMap<String, Object>();
		if(bindingResult.hasErrors()) {	
			
			map.put("wellTypeEnum", WellType.values());
			map.put("reservoirTypeEnum", ReservoirType.values());
			
			map.put("message", "Insertion failed !");
			map.put("alertClass", "alert-warning");
			
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(EDIT_PAGE);
		} else {
			
			// reMap Well before saving it
						
			Optional<WellMapping> opWM = wellMappingDAO.findByWid(well.getUid());
			
			if (opWM.isPresent()) {
				WellMapping wm=opWM.get();
				
				if (wm.getFid() != 0) {
				  Field field = fieldService.findFieldById(wm.getFid()).get();
				  well.setField(field);
				}
				if(wm.getPid() != 0) {
				  Perimeter perimeter = perimeterService.findPerimeterById(wm.getPid()).get();
				  well.setPerimeter(perimeter);
				}
				
			}
			
			wellService.saveWell(well);	
			
			Page<Well> page = wellService.findAll(1, "WellName","asc");
			
			int totalItems = page.getNumberOfElements();
			int totalPages = page.getTotalPages();
			
			/*
			 * FIXME : DRY this code ! 
			 */
			map.put("wells", page.getContent());
			map.put("currentPage", 1);
			map.put("totalItems", totalItems);
			map.put("totalPages", totalPages);
			map.put("field", "wellName");
			map.put("direction", "asc");
			
			map.put("message", "The Well <" + well.getWellName().toUpperCase() + "> has been updated successfully !");
			map.put("alertClass", "alert-info");
			
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(ALL_PAGE);
		}		
		return modelAndView;
	}	
	
}
