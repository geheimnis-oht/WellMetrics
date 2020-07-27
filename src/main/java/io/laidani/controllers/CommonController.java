package io.laidani.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.laidani.models.Perimeter;
import io.laidani.models.ReservoirType;
import io.laidani.models.Well;
import io.laidani.models.WellType;
import io.laidani.services.IPerimeterService;
import io.laidani.services.IWellService;

@Controller
public class CommonController {
	
	public static final String HOME_PAGE = "HomePage";
	
	@Autowired
	IWellService wellService;
	
	@Autowired
	IPerimeterService perimeterService;
	
	
	@GetMapping("/home")
	public String goHome() {
		return HOME_PAGE;
	}
	
	@GetMapping ("/test")
	public String insert() {
		Well well = new Well();
		well.setLatitude(new BigDecimal(45.78));
		well.setLongitude(new BigDecimal(-66.98));
		well.setMecanoCode("SXS");
		well.setReservoirType(ReservoirType.F6);
		well.setWellType(WellType.AGL);
		well.setWellName("TST983");
		
		Perimeter perimeter = perimeterService.findPerimeterById(3).get();
		well.setPerimeter(perimeter);
		
		wellService.saveWell(well);
		
		return HOME_PAGE;
	}
	

}
