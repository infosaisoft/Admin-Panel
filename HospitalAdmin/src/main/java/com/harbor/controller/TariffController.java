package com.harbor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	
	String uid = null;

	ServletContext sc = null;

	@RequestMapping(value = "tariff", method = RequestMethod.GET)
	public String ShowTariff(Map<String, Object> map, @ModelAttribute("trafficCmd") TariffCommand trafficCmd,
			HttpServletRequest reqbg) {
		sc = reqbg.getServletContext();
		uid = (String) sc.getAttribute("uid");
		List<TariffDto> listdto = null;
		List<TariffDto> ratelistdto = null;
		String hid = (String) sc.getAttribute("hid");

		if (uid == null) {
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
			String hid = null;
			TariffDto tardto = null;
			String insert = null;
			sc = req.getServletContext();
			List<TariffDto> listdto = null;
	
			hid = (String) sc.getAttribute("hid");
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
		sc = req.getServletContext();
		String delete = null;
		TariffDto tardto = null;

		uid = (String) sc.getAttribute("uid");
		String tariff_id = req.getParameter("tariff_id");
		List<TariffDto> listdto = null;
		String hid = (String) sc.getAttribute("hid");
	
		
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
		sc = req.getServletContext();
		String deleteRate = null;
		TariffDto tardto = null;

		uid = (String) sc.getAttribute("uid");
		String rate_id = req.getParameter("rate_id");
		List<TariffDto> listdto = null;
		String hid = (String) sc.getAttribute("hid");
	
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
		String hid = null;
		sc = req.getServletContext();
		List<String>getname=new ArrayList<>();
		List<TariffDto> listdto = new ArrayList<>();
		hid = (String) sc.getAttribute("hid");
		
		Map<String, Object> tarifflist = new HashMap<String, Object>();
		// use service
		listdto = tarService.featchAll(hid);
		
		for (TariffDto tdto : listdto) {
			getname.add(tdto.getTariff_name().toString());
		}
		tarifflist.put("getname", getname);
		
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
