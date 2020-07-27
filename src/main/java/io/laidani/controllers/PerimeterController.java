package io.laidani.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.laidani.models.Perimeter;
import io.laidani.models.Well;
import io.laidani.services.IPerimeterService;
import io.laidani.services.IWellService;
import io.laidani.services.WellService;

@Controller
@RequestMapping(path = "/api/perimeters")
public class PerimeterController implements WebMvcConfigurer {
    
	public static final String ADD_PERIMETER_PAGE = "addPerimeter";
	public static final String ALL_PERIMETERS_PAGE = "allPerimeters";
	public static final String ALL_PERIMETER_WELLS_PAGE = "perimeterWells";
	public static final String REDIRECT_ALL_PERIM = "redirect:/api/perimeters/all";
	public static final String MAP_WELL_TO_PERIMETER = "mapWellPerimeter";
	
	
	@Autowired
	IPerimeterService perimeterService;
	
	@Autowired
	IWellService wellService;
	
	@GetMapping(value = "/all")
	public ModelAndView getAllPerimeters(ModelAndView modelAndView) {
		modelAndView.addObject("perimeters", perimeterService.findAllPerimeters());
		modelAndView.setViewName(ALL_PERIMETERS_PAGE);
		return modelAndView;
	}
	
	@GetMapping(value = "/{id}/wells")
	public ModelAndView getAllPerimeters(@PathVariable(name = "id") int id, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
	    Optional<Perimeter> OpPerimeter = perimeterService.findPerimeterById(id);
		if (OpPerimeter.isPresent()) {
			List<Well> wells = OpPerimeter.get().getWells();
			map.put("wells", wells);
			map.put("perimeter", OpPerimeter.get());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(ALL_PERIMETER_WELLS_PAGE);
		}
		return modelAndView;
	}
	
	@GetMapping(value = "/{id}/wells/map")
	public ModelAndView mapWellToPerimeter(@PathVariable(name = "id") int id, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
	    Optional<Perimeter> OpPerimeter = perimeterService.findPerimeterById(id);
		if (OpPerimeter.isPresent()) {
			Well well = new Well();
			map.put("well", well);
			map.put("perimeter", OpPerimeter.get());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(MAP_WELL_TO_PERIMETER);
		}
		return modelAndView;
	}
	
	@PostMapping(value = "/{id}/wells/map/confirm")
	public ModelAndView confirmWellMapping(@PathVariable(name = "id") int id, Well well, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
	    Optional<Perimeter> OpPerimeter = perimeterService.findPerimeterById(id);
		if (OpPerimeter.isPresent()) {
			
			Well cleanWell = wellService.findWellById(well.getUid()).get();
			cleanWell.setPerimeter(OpPerimeter.get());
			wellService.saveWell(cleanWell);
			List<Well> wells = OpPerimeter.get().getWells();
			map.put("wells", wells);
			map.put("perimeter", OpPerimeter.get());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(ALL_PERIMETER_WELLS_PAGE);
		}
		return modelAndView;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView addPerimeter(ModelAndView modelAndView) {
		Perimeter perimeter = new Perimeter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		// put Objects in a Map
		map.put("perimeter", perimeter);
		
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(ADD_PERIMETER_PAGE);
		return modelAndView;
	}
	
	@PostMapping(value = "/save")
	public ModelAndView savePerimeter(@Valid Perimeter perimeter, BindingResult bindingResult, ModelAndView modelAndView) {
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName(ADD_PERIMETER_PAGE);			
		} else {
			perimeterService.savePerimeter(perimeter);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("perimeters", perimeterService.findAllPerimeters());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(REDIRECT_ALL_PERIM);
		}
		
		return modelAndView;
	}
	
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView deletePerimeter(@PathVariable(name="id") int id , ModelAndView modelAndView) {
		perimeterService.deletePerimeter(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("perimeters", perimeterService.findAllPerimeters());
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(REDIRECT_ALL_PERIM);
		return modelAndView;
	}
	
	
	
}
