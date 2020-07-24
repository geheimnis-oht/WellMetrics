package io.laidani.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.laidani.models.Perforation;
import io.laidani.models.Well;
import io.laidani.models.WellFieldType;
import io.laidani.models.WellTest;
import io.laidani.models.WellType;
import io.laidani.services.IWellService;

@Repository
@RequestMapping(path = "/api/wells")
public class WellController implements WebMvcConfigurer {
	
	public static final String ALL_WELLS_PAGE = "allWells";
	public static final String ADD_WELL_PAGE = "addWell";	
	
	@Autowired
	IWellService wellService;
	
	@GetMapping ("/hello")
	public String Hello() {
		return "HelloPage";
	}
	
	@GetMapping(value = "/all")
	public ModelAndView getAllWells(ModelAndView modelAndView) {
		modelAndView.addObject("wells", wellService.findAllWells());
		modelAndView.setViewName(ALL_WELLS_PAGE);
		return modelAndView;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView addWell(ModelAndView modelAndView) {
		Well well = new Well();
		well.setPerfos(new ArrayList<Perforation>());
		well.setTests(new ArrayList<WellTest>());
		Map<String, Object> map = new HashMap<String, Object>();
		
		// put Objects in a Map
		map.put("newWell", well);
		map.put("wellTypeEnum", WellType.values());
		map.put("wellFieldTypeEnum", WellFieldType.values());
		
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(ADD_WELL_PAGE);
		return modelAndView;
	}
	
}
