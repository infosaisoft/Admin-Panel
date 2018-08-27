package com.harbor.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.harbor.command.TariffCommand;
import com.harbor.dto.TariffDto;
import com.harbor.service.TariffService;

@Controller
public class TariffController {

	@Autowired
	TariffService tarService;
	String uid = null;

	ServletContext sc = null;

	@RequestMapping(value = "tariff", method = RequestMethod.GET)
	public String ShowTariff(Map<String, Object> map, @ModelAttribute("trafficCmd") TariffCommand trafficCmd,
			HttpServletRequest reqbg) {
		sc = reqbg.getServletContext();
		uid = (String) sc.getAttribute("uid");
		List<TariffDto> listdto = null;
		String hid = (String) sc.getAttribute("hid");

		if (uid == null) {
			return "redirect:/login";

		}
		// use service
		listdto = tarService.featchAll(hid);

		System.out.println("controller" + listdto);
		map.put("listdto", listdto);
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

}
