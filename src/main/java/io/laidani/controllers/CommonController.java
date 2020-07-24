package io.laidani.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	public static final String HOME_PAGE = "HomePage";
	
	@GetMapping("/home")
	public String goHome() {
		return HOME_PAGE;
	}

}
