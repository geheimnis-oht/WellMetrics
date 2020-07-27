package io.laidani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.laidani.dtos.WellDTO;
import io.laidani.mappers.IWellMapper;
import io.laidani.models.Well;
import io.laidani.services.IWellService;

@RestController
@RequestMapping("/api/rest/wells")
public class WellRESTController {
	
	@Autowired
	IWellService wellService;
	
	@Autowired
	IWellMapper mapper;
	
	@GetMapping(value = "/all")
	public List<Well> getAllWells() {
		return wellService.findAllWells();
	}
	
	@GetMapping(value = "/dtos/all")
	public List<WellDTO> getAllWellDTOs() {
		return mapper.wellsToWellDTOs(wellService.findAllWells());
	}


}
