package io.laidani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.laidani.models.Field;
import io.laidani.services.IFieldService;

@RestController
@RequestMapping("/api/rest/fields")
public class FieldRESTController {
	
	@Autowired
	IFieldService fieldService;
	
	@GetMapping(value = "/all")
	public List<Field> getAllFields() {
		return fieldService.findAllFields();
	}

}
