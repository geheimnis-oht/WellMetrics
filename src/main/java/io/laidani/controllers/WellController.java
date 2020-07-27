package io.laidani.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.laidani.models.Perforation;
import io.laidani.models.Perimeter;
import io.laidani.models.ReservoirType;
import io.laidani.models.Well;
import io.laidani.models.WellTest;
import io.laidani.models.WellType;
import io.laidani.services.IWellService;

@Repository
@RequestMapping(path = "/api/wells")
public class WellController implements WebMvcConfigurer {
	
	public static final String ALL_PAGE = "allWells";
	public static final String ADD_PAGE = "addWell";	
	public static final String REDIRECT_ALL_WELLS ="redirect:/api/wells/all";
	
	@Autowired
	IWellService wellService;
	
	@GetMapping ("/hello")
	public String Hello() {
		return "HelloPage";
	}
	
	@GetMapping(value = "/all")
	public ModelAndView getAllWells(ModelAndView modelAndView) {
		modelAndView.addObject("wells", wellService.findAllWells());
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
	public ModelAndView saveWell(@Valid Well well, BindingResult bindingResult, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(bindingResult.hasErrors()) {		
			map.put("wellTypeEnum", WellType.values());
			map.put("reservoirTypeEnum", ReservoirType.values());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(ADD_PAGE);
		} else {
			wellService.saveWell(well);			
			map.put("wells", wellService.findAllWells());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(REDIRECT_ALL_WELLS);
		}		
		return modelAndView;
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView deleteWell(@PathVariable(name = "id") int id, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		wellService.deleteWell(id);			
		map.put("wells", wellService.findAllWells());
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(REDIRECT_ALL_WELLS);
		
		return modelAndView;
	}
	
}
