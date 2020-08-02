package io.laidani.controllers;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.laidani.models.Perimeter;
import io.laidani.models.ReservoirType;
import io.laidani.models.Well;
import io.laidani.models.WellType;
import io.laidani.services.IPerimeterService;
import io.laidani.services.IWellService;

@Controller
public class CommonController {
	
	public static final String HOME_PAGE = "HomePage";
	public static final String TEST_PAGE = "testingPage";
	
	
	@Autowired
	IWellService wellService;
	
	@Autowired
	IPerimeterService perimeterService;
	
	
	@GetMapping("/home")
	public String goHome() {
		return HOME_PAGE;
	}
	
	@GetMapping("/t")
	public String testPage() {
		return "testPage";
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
	
	@GetMapping ("/attribs/{alert}")
	public String attrib(RedirectAttributes rattribs, @PathVariable(name ="alert") int alert) {
		
		if (alert == 1) 
			rattribs.addFlashAttribute("alertClass", "alert-success");
		else rattribs.addFlashAttribute("alertClass", "alert-warning");
		
		rattribs.addFlashAttribute("message", "Hello this is me !");
		
		return "redirect:/home";	
	}
	
	@GetMapping ("/mav")
	public String mav(Model mav, RedirectAttributes rattribs) {
	
	//  TECH : with Map it will not work !!
	/*	Map<String, Object> map = new HashMap<String, Object>();
		map.put("List", Arrays.asList(4,7,8,9));
		map.put("message","This is my message to the World !" );
		map.put("alertClass", "alert-warning");
	*/	
//		mav.addObject("List", Arrays.asList(4,7,8,9));
//		//mav.addObject("well", wellService.findAllWells());
//     	mav.addObject("message","[MAV] This is my message to the World !" );
//		mav.addObject("alertClass", "alert-warning");
//		
		// doesn't work with redirect:/home
		//mav.setViewName("redirect:/home");
		
		rattribs.addFlashAttribute("alertClass", "alert-success");
		rattribs.addFlashAttribute("message","[RATTRIBS] This is my message to the World !");
		mav.addAttribute("well", wellService.findAllWells());
		
		return "redirect:/home";
	}
	
	@GetMapping ("/international")
	public String inter() {
		
		return TEST_PAGE;
	}
	

}
