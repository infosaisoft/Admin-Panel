package com.harbor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppointmentController {
	@RequestMapping(value="/appointment-setup", method=RequestMethod.GET)
	public String homePage() {
		
		return "appointment-setup";
	}
}
