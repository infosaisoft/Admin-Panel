package com.harbor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.harbor.command.TariffCommand;
import com.harbor.dto.TariffDto;
import com.harbor.dto.UserDto;
import com.harbor.service.TariffService;

@Controller
@SessionAttributes({ "hid", "uid" })
@Scope("session")
public class TariffController {

	
	@Autowired
	TariffService tarService;

	@Autowired
	AppointmentController ap;
	
	@Autowired
	HomeController hc;
	
	long uid = 0;

	HttpSession ses = null;
	
	
	//get active url   
			@ModelAttribute("activeurl")
			public Map<String, Object> getActiveUrl(HttpServletRequest req) {
				Map<String, Object> activeurl = new HashMap<String, Object>();
				
				String url="tariff";
				
				activeurl.put("url", url);
				return activeurl;
			}
		

	@RequestMapping(value = "tariff", method = RequestMethod.GET)
	public String ShowTariff(Map<String, Object> map, @ModelAttribute("trafficCmd") TariffCommand trafficCmd,
			HttpServletRequest reqbg) {
		ses = reqbg.getSession();
		uid = (long) ses.getAttribute("uid");
		List<TariffDto> listdto = null;
		List<TariffDto> ratelistdto = null;
		long hid = (long) ses.getAttribute("hid");

		if (uid == 0) {
			return "redirect:/login";

		}
		// use service
		listdto = tarService.featchAll(hid);
		ratelistdto = tarService.featchAllRates(hid);

		map.put("listdto", listdto);
		map.put("ratelistdto", ratelistdto);
		return "tariff";
	}

	@RequestMapping(value = "tariff", method = RequestMethod.POST)
	public String RegistrationTariff(Map<String, Object> map, @ModelAttribute("trafficCmd") TariffCommand trafficCmd,
			HttpServletRequest req) {
			long hid = 0;
			TariffDto tardto = null;
			String insert = null;
			ses = req.getSession();
			List<TariffDto> listdto = null;
	
			hid = (long) ses.getAttribute("hid");
			String action = req.getParameter("action");
			
			if (action.equals("1")) {
				// copy cmd to dto
				tardto = new TariffDto();
				BeanUtils.copyProperties(trafficCmd, tardto);
		
				tardto.setHid(hid);
				// use service
				listdto = tarService.featchAll(hid);
				insert = tarService.regTraiff(tardto);
				map.put("insert", insert);
				map.put("listdto", listdto);
				return "redirect:/tariff";
			}
			else if(action.equals("2")){
				// copy cmd to dto
				tardto = new TariffDto();
				BeanUtils.copyProperties(trafficCmd, tardto);
		
				tardto.setHid(hid);
				// use service
				listdto = tarService.featchAll(hid);
				insert = tarService.addTariff(tardto);
				map.put("insert", insert);
				map.put("listdto", listdto);
				return "redirect:/tariff";
			}
			
			return "tariff";
		}
	
	
	// Delete Tariff
	@RequestMapping(value = "delete_tariff", method = RequestMethod.GET)
	public String deleteTariff(Map<String, Object> map, @ModelAttribute("trafficCmd") TariffCommand trafficCmd,
			HttpServletRequest req) {
		ses = req.getSession();
		String delete = null;
		TariffDto tardto = null;

		uid = (long) ses.getAttribute("uid");
		long tariff_id =Long.parseLong(req.getParameter("tariff_id"));
		List<TariffDto> listdto = null;
		long hid = (long) ses.getAttribute("hid");
	
		
		tardto = new TariffDto();
		BeanUtils.copyProperties(trafficCmd, tardto);

		tardto.setHid(hid);
		// use service
		listdto = tarService.featchAll(hid);
		delete = tarService.removeTariff(tariff_id);

		map.put("listdto", listdto);
		map.put("delete", delete);
		return "redirect:/tariff";
	}
	
	// Delete Rate
	@RequestMapping(value = "delete_rate", method = RequestMethod.GET)
	public String deleteRate(Map<String, Object> map, @ModelAttribute("trafficCmd") TariffCommand trafficCmd,
			HttpServletRequest req) {
		ses = req.getSession();
		String deleteRate = null;
		TariffDto tardto = null;

		uid = (long) ses.getAttribute("uid");
		long rate_id = Long.parseLong(req.getParameter("rate_id"));
		List<TariffDto> listdto = null;
		long hid = (long) ses.getAttribute("hid");
	
		tardto = new TariffDto();
		BeanUtils.copyProperties(trafficCmd, tardto);

		tardto.setHid(hid);
		// use service
		listdto = tarService.featchAll(hid);
		deleteRate = tarService.removeRate(rate_id);

		map.put("listdto", listdto);
		map.put("deleteRate", deleteRate);
		return "redirect:/tariff";
	}
	
	// tariff list
	@ModelAttribute("tarifflist")
	private Map<String, Object> getTariffs(HttpServletRequest req) {
		long hid = 0;
		ses = req.getSession();
		List<Object>getname=new ArrayList<>();
	
		List<TariffDto> listdto = new ArrayList<>();
		hid = (long) ses.getAttribute("hid");
		long id=0;
		
		Map<String, Object> tarifflist = new HashMap<String, Object>();
		// use service
		listdto = tarService.featchAll(hid);
		
		for (TariffDto tdto : listdto) {
		   id=tdto.getTariff_id();
			getname.add(tdto.getTariff_name().toString());
			 
		}             
		tarifflist.put("getname", getname);
		tarifflist.put("id", id);
		
		return tarifflist;

	}
	
	// get doctor list from appointment controller
	@ModelAttribute("doclist")
	public Map<String, Object> getAllDoc(HttpServletRequest req) {		
		return ap.getRoles(req);
	}
	
	// get department list from Home controller
	@ModelAttribute("dptlist")
	public Map<String, Object> getAllDpt(HttpServletRequest req) {		
		return hc.getDepartment(req);
	}
	
}
