package com.harbor.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

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
		String result = null;
		LoginDto logindto = null;
		HttpSession ses = null;
		ses = req.getSession();
		String sesID = ses.getId();

		// copy command to dto
		logindto = new LoginDto();

		BeanUtils.copyProperties(loginPage, logindto);
		Date loginTime = new Date(ses.getCreationTime());
		logindto.setSession_id(sesID);
		logindto.setLogin_time(loginTime);
		ses.setAttribute("sesID", sesID);

		// Use Service
		result = loginservice.verifyUser(logindto);

		ses.setAttribute("uid", logindto.getAdmin_id());
		ses.setAttribute("hid", logindto.getHid());
	

		if (result.equalsIgnoreCase("success")) {

		return "redirect:home";

		}
		map.put("uid", logindto.getUsername());
		map.put("result", result);

		return "login";
	}

}
