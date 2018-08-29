package com.harbor.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	HomeController hc;

	@Autowired
	QueueService queueservice;

	HttpSession ses = null;

	@RequestMapping(value = "queue-management", method = RequestMethod.GET)
	public String showQueue(Map<String, Object> map, @ModelAttribute("queuecmd") QueueCommand queuecmd,
			HttpServletRequest req) {
		ses = req.getSession();
		String hid = null;
		List<QueueDto> listdto = null;
		hid = (String) ses.getAttribute("hid");

		listdto = queueservice.featchAllQueue(hid);

		map.put("listdto", listdto);

		return "queue-management";
	}

	@RequestMapping(value = "queue-management", method = RequestMethod.POST)
	public String processQueue(Map<String, Object> map, @ModelAttribute("queuecmd") QueueCommand queuecmd,
			HttpServletRequest req) {
		String insertQueue = null;
		ses = req.getSession();
		QueueDto queuedto = null;
		String hid = null;
		hid = (String) ses.getAttribute("hid");
		List<QueueDto> listdto = null;

		// copy cmd to dto
		queuedto = new QueueDto();
		BeanUtils.copyProperties(queuecmd, queuedto);
		queuedto.setHid(hid);
		
		//use service
		insertQueue = queueservice.registrationQueue(queuedto);
		listdto = queueservice.featchAllQueue(hid);

		map.put("insertQueue", insertQueue);
		map.put("listdto", listdto);

		return "queue-management";
	}

	@ModelAttribute("doclist")
	public Map<String, Object> getAllDoctor(HttpServletRequest req) {

		return ap.getRoles(req);
	}

	@ModelAttribute("dptlist")
	public Map<String, Object> getAllDdepartment(HttpServletRequest req) {

		return hc.getDepartment(req);
	}
	
	//delete queue
	@RequestMapping(value = "delete_queue", method = RequestMethod.GET)
	public String removeQueue(Map<String, Object> map, @ModelAttribute("queuecmd") QueueCommand queuecmd,
			HttpServletRequest req) {
		String deleteQueue = null;
		ses = req.getSession();
		QueueDto queuedto = null;
		String hid = null;
		String queue_id=null;
		hid = (String) ses.getAttribute("hid");
		List<QueueDto> listdto = null;
		queue_id=req.getParameter("queue_id");
		// copy cmd to dto
		queuedto = new QueueDto();
		BeanUtils.copyProperties(queuecmd, queuedto);
		queuedto.setHid(hid);
		
		//use service
		deleteQueue = queueservice.deleteQueue(queue_id);
		listdto = queueservice.featchAllQueue(hid);

		map.put("deleteQueue", deleteQueue);
		map.put("listdto", listdto);

		return "redirect:/queue-management";
	}
	

}
