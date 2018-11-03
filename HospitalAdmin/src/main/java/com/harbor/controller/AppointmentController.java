package com.harbor.controller;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.harbor.command.ShiftCommand;
import com.harbor.common.SpringException;
import com.harbor.commons.UniqueCouponValidator;
import com.harbor.dto.DoctorAvaliblityDto;
import com.harbor.dto.SettingsDto;
import com.harbor.dto.ShiftDto;
import com.harbor.dto.SlotDto;
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
	
	@Autowired
	HomeController hc;

	HttpSession ses = null;

	// get active url
	@ModelAttribute("activeurl")
	public Map<String, Object> getActiveUrl(HttpServletRequest req) {
		Map<String, Object> activeurl = new HashMap<String, Object>();
		String url = "appcontroller";
		activeurl.put("url", url);
		return activeurl;
	}

	// Page View
	@RequestMapping(value = "/appointment-setup", method = RequestMethod.GET)
	public String appointmentPage(Map<String, Object> map, @ModelAttribute("shiftadd") ShiftCommand shiftcmd,
			HttpServletRequest req, HttpServletResponse res,BindingResult error) {
		ses = req.getSession();
		List<DoctorAvaliblityDto> listdocdto = null;
		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");
		SettingsDto setdto = null;

		setdto = set_ser.fetchSet(hid);
		map.put("setdto", setdto);

		List<ShiftDto> listdto = null;

		if (uid == 0) {
			return "redirect:/login";
		}

		// use service
		listdocdto = avaliblitservice.featchAllAppotiment(hid);

		listdto = sht_ser.fetchAllShifts(hid);

		map.put("listdto", listdto);
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("listdocdto", listdocdto);

		return "appointment-setup";
	}

	// Post Shift Form
	@ExceptionHandler(value= {ConstraintViolationException.class,Exception.class})
	@RequestMapping(value = "/appointment-setup", method = RequestMethod.POST)
	public String insertShift(Map<String, Object> map, @ModelAttribute("shiftadd") ShiftCommand shiftcmd,
			HttpServletRequest req, HttpServletResponse res,BindingResult error) throws ParseException, SQLException {

		HttpSession ses = null;
		ses = req.getSession();
		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");

		List<ShiftDto> listdto = null;
		String action = req.getParameter("action");

		if (action.equals("1")) {
			ShiftDto shtDto = null;
			String result = null;
         
			listdto = sht_ser.fetchAllShifts(hid);
		LocalTime t1=LocalTime.parse(shiftcmd.getStart_time());
		
		LocalTime t2=LocalTime.parse(shiftcmd.getEnd_time());
	        
	        		
			 System.out.println("the time is:::::::::"+t1);
			// copy cmd to dto
			shtDto = new ShiftDto();
			shtDto.setHid(hid);
			
			
			BeanUtils.copyProperties(shiftcmd, shtDto);
			shtDto.setStart_time( Time.valueOf(t1));
			shtDto.setEnd_time( Time.valueOf(t2));
			if(error.hasErrors()) {
				
				return "redirect:/appointment-setup";
			}
			else {
			try {
				
			result = sht_ser.insertShift(shtDto);
			         
			  if(result.equals("success")) {
				  return "redirect:/appointment-setup";
			  }
			  else {
				  error.rejectValue("shiftname",shiftcmd.getShift_name(), "must not same");
			  }
			}
			
			
			catch(SpringException e) {
				error.reject("DUPKEY");
				 throw new SpringException("500", e.getMessage());
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				throw new SQLException("value shuld not be same");
			}
			}
	
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
			SettingsDto setDto = null, setdto1 = null;
			ShiftDto shiftdto = null;
			String insert_avail = null;
			DoctorAvaliblityDto docdto = null;
			SlotDto slotdto = null;
			String slotresult = null;

			listdto = sht_ser.fetchAllShifts(hid);

			// copy cmd to dto
			setDto = new SettingsDto();
			docdto = new DoctorAvaliblityDto();
			slotdto = new SlotDto();

			docdto.setHid(hid);
			setDto.setHid(hid);
			
	

			BeanUtils.copyProperties(shiftcmd, setDto);
			BeanUtils.copyProperties(shiftcmd, docdto);
                                  
			BeanUtils.copyProperties(docdto, slotdto);
	        
			slotdto.setDpt_id(Long.parseLong(docdto.getDpt_name()));
			slotdto.setDoc_id(Long.parseLong(docdto.getDoc_name()));
			System.out.println("slotdate:::::::::::::"+docdto.getDate());
			insert_avail = avaliblitservice.registration(docdto);
		
			map.put("docdto", docdto);

			map.put("uid", uid);
			map.put("hid", hid);
			// map.put("listdto", listdto);
			map.put("insert_avail", insert_avail);

			// for genrating shift wise appointment slots
			setdto1 = new SettingsDto();
			shiftdto = avaliblitservice.viewShifByName(hid, shiftcmd.getShift_name());

			// genrate slot
			slotresult = avaliblitservice.regisrationSlot(slotdto);

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

		long hid = (long) ses.getAttribute("hid");
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

		long hid = (long) ses.getAttribute("hid");
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
		List<Object> name = new ArrayList<>();
		ses = req.getSession();
		long hid = 0;
		long id = 0;
		String fname = null, name1 = null;
		List<UserDto> listdto = new ArrayList<>();
		hid = (long) ses.getAttribute("hid");
		listdto = docservice.featchRole(hid);
		for (UserDto dto : listdto) {

			id = dto.getAdmin_id();
			fname = dto.getFname();
            name.add(fname);

		}
		doclist.put("name", name);
		doclist.put("id", id);

		return doclist;
	}

	// get Shidts
	@ModelAttribute("shiftlist")
	private Map<String, Object> getShiftList(HttpServletRequest req) {
		Map<String, Object> shiftlist = new HashMap<String, Object>();
		long hid = (long) ses.getAttribute("hid");
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

	// get date
	@ModelAttribute("shiftdate")
	private Map<String, Object> getDate(HttpServletRequest req) {
		ses = req.getSession();
		Map<String, Object> shiftdate = new HashMap<String, Object>();
		long hid = (long) ses.getAttribute("hid");

		List<DoctorAvaliblityDto> docdto = null;
		List<Date> date = new ArrayList<>();
		Date shiftdate1 = null;
		// use service
		docdto = avaliblitservice.featchAllAppotiment(hid);

		for (DoctorAvaliblityDto dto : docdto) {
			shiftdate1 = dto.getDate();
			date.add(shiftdate1);
		}
		shiftdate.put("date", date);
		return shiftdate;
	}

	// get department list from Home controller
	@ModelAttribute("dptlist")
	public Map<String, Object> getAllDpt(HttpServletRequest req) {
		return hc.getDepartment(req);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	public ModelAndView exceptionHandler(Exception ex, Locale locale) {
	    String msg = ex.getMessage();
	    // or if you have a I18n app : String msg = messageSource.getMessage("DUPKEY", null, locale);
	    return new ModelAndView("appointment-setup", "msg", msg);
	}
	
	

}
