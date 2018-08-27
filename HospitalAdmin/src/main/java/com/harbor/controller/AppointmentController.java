package com.harbor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.harbor.command.ShiftCommand;
import com.harbor.dto.SettingsDto;
import com.harbor.dto.ShiftDto;
import com.harbor.service.SettingService;
import com.harbor.service.ShiftService;

@Controller
@SessionAttributes({"hid","uid"})
@Scope("session")
public class AppointmentController {

	@Autowired
	ShiftService sht_ser;
	@Autowired
	SettingService set_ser;

	ServletContext sc = null;

	// Page View
	@RequestMapping(value = "/appointment-setup", method = RequestMethod.GET)
	public String appointmentPage(Map<String, Object> map, @ModelAttribute("shiftadd") ShiftCommand shiftcmd,
			HttpServletRequest req, HttpServletResponse res) {

		sc = req.getServletContext();

		String uid = (String) sc.getAttribute("uid");
		String hid = (String) sc.getAttribute("hid");
		
		SettingsDto setdto=null;
		setdto = set_ser.fetchSet(hid);
		System.out.println("Controller === "+setdto);
		map.put("setdto", setdto);
		
		List<ShiftDto> listdto = null;

		if (uid == null) {
			return "redirect:/login";
		}

		listdto = sht_ser.fetchAllShifts(hid);
		System.out.println("Shifts== " + listdto);
		map.put("listdto", listdto);
		map.put("uid", uid);
		map.put("hid", hid);

		return "appointment-setup";
	}

	// Post Shift Form
	@RequestMapping(value = "/appointment-setup", method = RequestMethod.POST)
	public String insertShift(Map<String, Object> map, @ModelAttribute("shiftadd") ShiftCommand shiftcmd,
			HttpServletRequest req, HttpServletResponse res) {

		ServletContext sc = null;
		sc = req.getServletContext();
		String uid = (String) sc.getAttribute("uid");
		String hid = (String) sc.getAttribute("hid");

		List<ShiftDto> listdto = null;
		String action = req.getParameter("action");
		// System.out.println("action ========= "+action);

		if (action.equals("1")) {
			ShiftDto shtDto = null;
			String result = null;

			listdto = sht_ser.fetchAllShifts(hid);

			// copy cmd to dto
			shtDto = new ShiftDto();
			shtDto.setHid(hid);
			BeanUtils.copyProperties(shiftcmd, shtDto);

			result = sht_ser.insertShift(shtDto);

			map.put("uid", uid);
			map.put("hid", hid);
			map.put("result", result);
			//map.put("listdto", listdto);
			return "redirect:/appointment-setup";
			
		}

		if (action.equals("2")) {
			SettingsDto setDto = null;
			String result_set = null;

			listdto = sht_ser.fetchAllShifts(hid);

			// copy cmd to dto
			setDto = new SettingsDto();
			setDto.setHid(hid);
			BeanUtils.copyProperties(shiftcmd, setDto);

			result_set = set_ser.insertSetting(setDto);
			map.put("uid", uid);
			map.put("hid", hid);
			//map.put("listdto", listdto);
			map.put("result_set", result_set);
		}
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("listdto", listdto);

		return "appointment-setup";

	}

	// Delete Shift Data
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
		map.put("delete", delete);
		return "redirect:/appointment-setup";
	}
	
	// get doctors
	@ModelAttribute("doclist")
	private Map<String, String> getRoles() {
		Map<String, String> rolelist = new HashMap<String, String>();
		rolelist.put("doctor", "Doctor");
		rolelist.put("asst_doc", "Assitant Doctor");
		rolelist.put("nurse", "Nurse");
		rolelist.put("reception", "Reception");
		rolelist.put("billing", "Billing");
		rolelist.put("admin", "Admin");
		return rolelist;
	}
	
	
}
