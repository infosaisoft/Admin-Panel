package com.harbor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.harbor.command.ShiftCommand;
import com.harbor.dto.DoctorAvaliblityDto;
import com.harbor.dto.SettingsDto;
import com.harbor.dto.ShiftDto;
import com.harbor.dto.UserDto;
import com.harbor.service.DocAvailService;
import com.harbor.service.DoctorAvalibityService;
import com.harbor.service.SettingService;
import com.harbor.service.ShiftService;

@Controller
@SessionAttributes({ "hid", "uid" })
@Scope("session")
public class AppointmentController {

	@Autowired
	ShiftService sht_ser;
	@Autowired
	SettingService set_ser;

	@Autowired
	DocAvailService docservice;

	@Autowired
	DoctorAvalibityService avaliblitservice;

	HttpSession ses =null;
	
	//get active url   
		@ModelAttribute("activeurl")
		public Map<String, Object> getActiveUrl(HttpServletRequest req) {
			Map<String, Object> activeurl = new HashMap<String, Object>();
			
			String url="appcontroller";
			
			activeurl.put("url", url);
			return activeurl;
		}
	

	// Page View
	@RequestMapping(value = "/appointment-setup", method = RequestMethod.GET)
	public String appointmentPage(Map<String, Object> map, @ModelAttribute("shiftadd") ShiftCommand shiftcmd,
			HttpServletRequest req, HttpServletResponse res) {

		ses = req.getSession();
		List<DoctorAvaliblityDto> listdocdto = null;

		String uid = (String) ses.getAttribute("uid");
		String hid = (String) ses.getAttribute("hid");

		SettingsDto setdto = null;
		setdto = set_ser.fetchSet(hid);
		map.put("setdto", setdto);

		List<ShiftDto> listdto = null;

		if (uid == null) {
			return "redirect:/login";
		}

		// use service
		listdocdto = avaliblitservice.featchAllAppotiment(hid);

		listdto = sht_ser.fetchAllShifts(hid);
		System.out.println("Shifts== " + listdto);
		map.put("listdto", listdto);
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("listdocdto", listdocdto);

		return "appointment-setup";
	}

	// Post Shift Form
	@RequestMapping(value = "/appointment-setup", method = RequestMethod.POST)
	public String insertShift(Map<String, Object> map, @ModelAttribute("shiftadd") ShiftCommand shiftcmd,
			HttpServletRequest req, HttpServletResponse res) {

		HttpSession ses = null;
		ses = req.getSession();
		String uid = (String) ses.getAttribute("uid");
		String hid = (String) ses.getAttribute("hid");

		List<ShiftDto> listdto = null;
		String action = req.getParameter("action");

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
			return "redirect:/appointment-setup";

		}

		else if (action.equals("2")) {
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
			// map.put("listdto", listdto);
			map.put("result_set", result_set);
			return "redirect:/appointment-setup";

		}

		else if (action.equals("3")) {
			SettingsDto setDto = null;
			String insert_avail = null;
			DoctorAvaliblityDto docdto = null;

			listdto = sht_ser.fetchAllShifts(hid);

			// copy cmd to dto
			setDto = new SettingsDto();
			docdto = new DoctorAvaliblityDto();
			docdto.setHid(hid);
			setDto.setHid(hid);
			BeanUtils.copyProperties(shiftcmd, setDto);
			BeanUtils.copyProperties(shiftcmd, docdto);

			insert_avail = avaliblitservice.registration(docdto);

			map.put("docdto", docdto);
			map.put("uid", uid);
			map.put("hid", hid);
			// map.put("listdto", listdto);
			map.put("insert_avail", insert_avail);
			return "redirect:/appointment-setup";
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
		ses = req.getSession();

		String hid = (String) ses.getAttribute("hid");
		String delete = null;
		delete = sht_ser.removeShift(shift_id);
		List<ShiftDto> shiftdto = null;

		shiftdto = sht_ser.fetchAllShifts(hid);
		map.put("shiftdto", shiftdto);
		map.put("delete", delete);
		return "redirect:/appointment-setup";
	}

	// Delete doctor avaliblity
	@RequestMapping(value = "/delete_app", method = RequestMethod.GET)
	public String deleteAvaliblity(HttpServletRequest req, Map<String, Object> map,
			@ModelAttribute("shiftadd") ShiftCommand shiftcmd) {

		String avail_id = req.getParameter("avail_id");
		ses = req.getSession();

		String hid = (String) ses.getAttribute("hid");
		String delete = null;
		delete = avaliblitservice.remooveAvaliblity(avail_id);
		List<ShiftDto> shiftdto = null;

		shiftdto = sht_ser.fetchAllShifts(hid);
		map.put("shiftdto", shiftdto);
		map.put("delete", delete);
		return "redirect:/appointment-setup";
	}

	// get doctors
	@ModelAttribute("doclist")
	public Map<String, Object> getRoles(HttpServletRequest req) {

		Map<String, Object> doclist = new HashMap<String, Object>();
		List<String> name = new ArrayList<>();
		ses = req.getSession();
		String hid = null;
		String fname = null, lname = null, name1 = null;
		List<UserDto> listdto = new ArrayList<>();
		hid = (String) ses.getAttribute("hid");
		listdto = docservice.featchRole(hid);
		for (UserDto dto : listdto) {
			fname = dto.getFname();
			lname = dto.getLname();
			name1 = fname + " " + lname;
			name.add(name1);

		}
		doclist.put("name", name);

		return doclist;
	}

	// get Shidts
	@ModelAttribute("shiftlist")
	private Map<String, Object> getShiftList(HttpServletRequest req) {
		Map<String, Object> shiftlist = new HashMap<String, Object>();
		String hid = (String) ses.getAttribute("hid");
		List<ShiftDto> shiftdto = null;
		List<String> shift = new ArrayList<>();
		String shiftname = null;
		// use service
		shiftdto = sht_ser.fetchAllShifts(hid);

		for (ShiftDto dto : shiftdto) {
			shiftname = dto.getShift_name();
			shift.add(shiftname);
		}
		shiftlist.put("shift", shift);
		return shiftlist;
	}
	
		
}
