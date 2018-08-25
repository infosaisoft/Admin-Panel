package com.harbor.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.harbor.command.ShiftCommand;
import com.harbor.command.UserCommand;
import com.harbor.dto.DepartmentDto;
import com.harbor.dto.ShiftDto;
import com.harbor.dto.UserDto;
import com.harbor.service.ShiftService;

@Controller
public class AppointmentController {
	
	@Autowired
	ShiftService sht_ser;
	
	ServletContext sc = null;
	
	@RequestMapping(value="/appointment-setup", method=RequestMethod.GET)
	public String appointmentPage(Map<String, Object> map, @ModelAttribute("shiftadd") ShiftCommand shiftcmd,
			HttpServletRequest req, HttpServletResponse res) {
		
		sc = req.getServletContext();
		
		String uid = (String) sc.getAttribute("uid");
		String hid = (String) sc.getAttribute("hid");
		
		List<ShiftDto> listdto = null;
		
		if(uid==null) {
			return "redirect:/login";
		}
		
		listdto = sht_ser.fetchAllShifts(hid);
		System.out.println("Shifts== "+listdto);
		map.put("listdto", listdto);
		map.put("uid", uid);
		map.put("hid", hid);
		
		return "appointment-setup";
	}
	
	@RequestMapping(value="/appointment-setup", method=RequestMethod.POST)
	public String insertShift(Map<String, Object> map, @ModelAttribute("shiftadd") ShiftCommand shiftcmd,
			HttpServletRequest req, HttpServletResponse res) {
		
		ShiftDto shtDto = null;
		ServletContext sc = null;
		sc = req.getServletContext();
		String result = null;
		List<ShiftDto> listdto = null;
				
		String uid = (String) sc.getAttribute("uid");
		String hid = (String) sc.getAttribute("hid");
		
		// copy cmd to dto
		shtDto = new ShiftDto();
		shtDto.setHid(hid);
		BeanUtils.copyProperties(shiftcmd, shtDto);

		result = sht_ser.insertShift(shtDto);
		listdto = sht_ser.fetchAllShifts(hid);
		
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("result", result);
		map.put("listdto", listdto);
		
		return "appointment-setup";
	}
	
	@RequestMapping(value = "/delete_shift", method = RequestMethod.GET)
	public String deleteShift(HttpServletRequest req, Map<String, Object> map,
			@ModelAttribute("shiftadd") ShiftCommand shiftcmd) {

		String shift_id = req.getParameter("sft_id");
		sc = req.getServletContext();

		String hid = (String) sc.getAttribute("hid");
		String delete = null;
		delete = sht_ser.removeShift(shift_id);
		List<ShiftDto> shiftdto = null;

		shiftdto = sht_ser.fetchAllShifts(hid);
		map.put("shiftdto", shiftdto);
		System.out.println(delete);
		map.put("delete", delete);
		return "redirect:/appointment-setup";
	}
	
}
