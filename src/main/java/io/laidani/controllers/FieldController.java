package io.laidani.controllers;

import java.util.HashMap;
import java.util.Map;

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

import io.laidani.models.Field;
import io.laidani.services.IFieldService;

@Controller
@RequestMapping(path = "/api/fields")
public class FieldController implements WebMvcConfigurer {

	public static final String ADD_FIELD_PAGE = "addField";
	public static final String ALL_FIELDS_PAGE = "allFields";
	public static final String REDIRECT_ALL_FIELDS = "redirect:/api/fields/all";
	
	
	@Autowired
	IFieldService fieldService;

	@GetMapping(value = "/add")
	public ModelAndView addField(ModelAndView modelAndView) {
		Field field = new Field();
		Map<String, Object> map = new HashMap<String, Object>();
		
		// put Objects in a Map
		map.put("field", field);
		
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(ADD_FIELD_PAGE);
		return modelAndView;
	}

	@GetMapping(value = "/delete/{id}")
	public ModelAndView deleteField(@PathVariable(name="id") int id , ModelAndView modelAndView) {
		fieldService.deleteField(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fields", fieldService.findAllFields());
		modelAndView.addAllObjects(map);
		modelAndView.setViewName(REDIRECT_ALL_FIELDS);
		return modelAndView;
	}

	@GetMapping(value = "/all")
	public ModelAndView getAllFields(ModelAndView modelAndView) {
		modelAndView.addObject("fields", fieldService.findAllFields());
		modelAndView.setViewName(ALL_FIELDS_PAGE);
		return modelAndView;
	}

	@PostMapping(value = "/save")
	public ModelAndView saveField(@Valid Field field, BindingResult bindingResult, ModelAndView modelAndView) {
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName(ADD_FIELD_PAGE);			
		} else {
			fieldService.saveField(field);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fields", fieldService.findAllFields());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(REDIRECT_ALL_FIELDS);
		}
		
		return modelAndView;
	}
	
	

}
