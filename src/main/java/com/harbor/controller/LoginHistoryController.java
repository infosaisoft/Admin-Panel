package com.harbor.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.harbor.command.LoginHistoryCommand;
import com.harbor.dto.LoginHistoryDto;
import com.harbor.service.LoginHistoryService;

@Controller
@SessionAttributes({"hid","uid"})
@Scope("session")
public class LoginHistoryController {
	
	@Autowired
	LoginHistoryService loginser = null;
	
	HttpSession ses =null;
	
	@RequestMapping(value = "/login-history", method = RequestMethod.GET)
	public String loginHistory(Map<String, Object> map, HttpServletRequest req, HttpServletResponse res) {
		ses = req.getSession();
		String uid = (String) ses.getAttribute("uid");
		String hid = (String) ses.getAttribute("hid");
		
		String admin_id = req.getParameter("admin_id");
		
		LoginHistoryDto dto = null;
		List<LoginHistoryDto> listdto = null;
		
		if(uid==null) {
			return "redirect:/login";
		}

		// copy cmd to dto
		dto = new LoginHistoryDto();
		
		// use service
		listdto = loginser.fetchLoginHistory(admin_id);
		map.put("listdto", listdto);
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("dto", dto);
		return "login-history";
	}
	
}
