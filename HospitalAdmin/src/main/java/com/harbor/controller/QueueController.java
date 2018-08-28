package com.harbor.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.harbor.command.QueueCommand;
import com.harbor.dto.QueueDto;
import com.harbor.service.QueueService;

@Controller
@SessionAttributes({ "hid", "uid" })
@Scope("session")
public class QueueController {
	@Autowired
	AppointmentController ap;
	
	
	@Autowired
	QueueService queueservice;
	
	
	ServletContext sc = null;

	@RequestMapping(value = "queue-management", method = RequestMethod.GET)
	public String showQueue(@ModelAttribute("queuecmd") QueueCommand queuecmd, HttpServletRequest req) {
		sc = req.getServletContext();
		String hid = null;
		hid = (String) sc.getAttribute("hid");
		return "queue-management";
	}
	
	
	
	@RequestMapping(value = "queue-management", method = RequestMethod.POST)
	public String processQueue(Map<String,Object>map,@ModelAttribute("queuecmd") QueueCommand queuecmd, HttpServletRequest req) {
		String insertQueue=null;
		sc = req.getServletContext();
		QueueDto queuedto=null;
		String hid = null;
		hid = (String) sc.getAttribute("hid");
		
		
		//copy cmd to dto
		queuedto=new QueueDto();
		BeanUtils.copyProperties(queuecmd, queuedto);
		insertQueue=queueservice.registrationQueue(queuedto);
		
		map.put("insertQueue", insertQueue);

		return "queue-management";
	}

	@ModelAttribute("doclist")
	public Map<String, Object> getAllDoctor(HttpServletRequest req) {
		
		return ap.getRoles(req);
	}
	
	
	@ModelAttribute("dptlist")
	public Map<String, Object> getAllDdepartment(HttpServletRequest req) {
		
		return ap.getRoles(req);
	}

}
