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

import io.laidani.models.Field;
import io.laidani.models.Perimeter;
import io.laidani.models.Well;
import io.laidani.services.IFieldService;
import io.laidani.services.IWellService;

@Controller
@RequestMapping(path = "/api/fields")
public class FieldController implements WebMvcConfigurer {

	public static final String ADD_FIELD_PAGE = "addField";
	public static final String ALL_FIELDS_PAGE = "allFields";
	public static final String REDIRECT_ALL_FIELDS = "redirect:/api/fields/all";
	
	public static final String ALL_FIELD_WELLS_PAGE = "fieldWells";
	public static final String MAP_WELL_TO_FIELD = "mapWellField";
	
	
	@Autowired
	IFieldService fieldService;
	
	@Autowired
	IWellService wellService;

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

	@GetMapping(value = "/{id}/wells")
	public ModelAndView getAllFields(@PathVariable(name = "id") int id, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
	    Optional<Field> opField = fieldService.findFieldById(id);
		if (opField.isPresent()) {
			List<Well> wells = opField.get().getWells();
			map.put("wells", wells);
			map.put("field", opField.get());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(ALL_FIELD_WELLS_PAGE);
		}
		return modelAndView;
	}
	
	@PostMapping(value = "/{id}/wells/map/confirm")
	public ModelAndView confirmWellMapping(@PathVariable(name = "id") int id, Well well, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
	    Optional<Field> opField = fieldService.findFieldById(id);
		if (opField.isPresent()) {
			
			Well cleanWell = wellService.findWellById(well.getUid()).get();
			cleanWell.setField(opField.get());
			wellService.saveWell(cleanWell);
			List<Well> wells = opField.get().getWells();
			map.put("wells", wells);
			map.put("field", opField.get());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(ALL_FIELD_WELLS_PAGE);
		}
		return modelAndView;
	}

	@GetMapping(value = "/{id}/wells/map")
	public ModelAndView mapFieldToPerimeter(@PathVariable(name = "id") int id, ModelAndView modelAndView) {
		Map<String, Object> map = new HashMap<String, Object>();
	    Optional<Field> opField = fieldService.findFieldById(id);
		if (opField.isPresent()) {
			Well well = new Well();
			map.put("well", well);
			map.put("field", opField.get());
			modelAndView.addAllObjects(map);
			modelAndView.setViewName(MAP_WELL_TO_FIELD);
		}
		return modelAndView;
	}
	
}
