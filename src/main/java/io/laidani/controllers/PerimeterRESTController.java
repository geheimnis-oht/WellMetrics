package io.laidani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.laidani.models.Perimeter;
import io.laidani.services.IPerimeterService;

@RestController
@RequestMapping("/api/rest/perimeters")
public class PerimeterRESTController {
	
	@Autowired
	IPerimeterService perimeterService;
	
	@GetMapping(value = "/all")
	public List<Perimeter> getAllPerimeters() {
		return perimeterService.findAllPerimeters();
	}

}
