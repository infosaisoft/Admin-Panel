package com.harbor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Logoutcontroller {
	
	@RequestMapping(method=RequestMethod.GET)
	  public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	  }

}
