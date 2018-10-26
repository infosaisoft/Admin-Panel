package com.harbor.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.harbor.service.LoginService;

@Controller
public class Logoutcontroller {
	
	@Autowired
	LoginService ser;

	@RequestMapping(method = RequestMethod.GET)
	public String logout(HttpSession session, Map<String, Object> map, HttpServletResponse res) {
		String sesID = (String) session.getAttribute("sesID");
		String update = ser.modifyLogoutTime(sesID);
		session.removeAttribute("hid");
		session.removeAttribute("uid");
		session.removeAttribute(sesID);

		session.invalidate();
		return "redirect:/login";
	}
}
