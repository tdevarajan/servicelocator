package com.xtime.servicelocator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xtime.servicelocator.service.DealerService;

@Controller
public class WelcomeController {


	@Autowired
	DealerService service;
	
	@Secured({"partner"})
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		return "serviceLocator";
	}
	
	@Secured({"partner"})
	@RequestMapping(value = "/spring", method = RequestMethod.GET)
	public String showSpringBootPage(ModelMap model) {
		return "home";
	}
	

}
