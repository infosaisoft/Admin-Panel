package com.harbor.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.harbor.command.LoginCommand;
import com.harbor.dto.LoginDto;
import com.harbor.service.LoginService;

@Controller
@SessionAttributes({"hid","uid"})
@Scope("session")
public class LoginController {

	@Autowired
	LoginService loginservice;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@ModelAttribute("loginPage") LoginCommand loginPage) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginVerify(Map<String, Object> map, @ModelAttribute("loginPage") LoginCommand loginPage,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ServletContext sc = null;
		String result = null;
		LoginDto logindto = null;
		

		// copy command to dto
		logindto = new LoginDto();
		BeanUtils.copyProperties(loginPage, logindto);
		sc = req.getServletContext();

		// Use Service
		result = loginservice.verifyUser(logindto);
		sc.setAttribute("uid", logindto.getAdmin_id());
		sc.setAttribute("hid", logindto.getHid());
		
		map.put("uid", logindto.getUsername());
		if (result.equalsIgnoreCase("success")) {

			return "redirect:home";

		}
		map.put("result", result);
		
		return "login";
	}

}
