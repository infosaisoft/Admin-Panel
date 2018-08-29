package com.harbor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.harbor.command.Departmentcommand;
import com.harbor.command.HospitalCommand;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dto.DepartmentDto;
import com.harbor.dto.HospitalDto;
import com.harbor.dto.UserDto;
import com.harbor.service.DepartmentService;
import com.harbor.service.HospitalService;

@Controller
@SessionAttributes({"hid","uid"})
@Scope("session")
public class HomeController {

	@Autowired
	HospitalService hservice = null;

	@Autowired
	DepartmentService dptService;
	
	HttpSession ses =null;
		  
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(Map<String, Object> map, @ModelAttribute("departmentcmd") Departmentcommand departmentcmd,
			HttpServletRequest req, HttpServletResponse res) {
		ses = req.getSession();
		String uid = (String) ses.getAttribute("uid");
		String hid = (String) ses.getAttribute("hid");
		HospitalDto dto = null;
		List<DepartmentDto> listdto = null;
		if(uid==null) {
			return "redirect:/login";
		}

		// copy cmd to dto
		dto = new HospitalDto();
		// use service
		dto = hservice.featchHospitalInfo(hid);
		listdto = dptService.featchAllDepartment(hid);
		map.put("listdto", listdto);
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("dto", dto);
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String showPage(Map<String, Object> map, @ModelAttribute("departmentcmd") Departmentcommand departmentcmd,
			HttpServletRequest req) {

		DepartmentDto dptdto = null;
		HttpSession ses = null;
		String result = null;
		List<DepartmentDto> listdto = null;
		HospitalDto dto = null;
		ses = req.getSession();
		String uid = (String) ses.getAttribute("uid");
		String hid = (String) ses.getAttribute("hid");

		// copy cmd to dto
		dptdto = new DepartmentDto();
		dto = new HospitalDto();

		BeanUtils.copyProperties(departmentcmd, dptdto);

		dptdto.setHid(hid);
		// use service

		dto = hservice.featchHospitalInfo(hid);
		result = dptService.registrationDepartment(dptdto);
		listdto = dptService.featchAllDepartment(hid);
		map.put("uid", uid);
		map.put("hid", hid);
		map.put("result", result);
		map.put("dto", dto);
		map.put("listdto", listdto);
		return "redirect:/home";
	}
	
	
	@RequestMapping(value = "/delete_dpt", method = RequestMethod.GET)
	public String deleteDepartment(Map<String, Object> map, @ModelAttribute("departmentcmd") Departmentcommand departmentcmd,
			HttpServletRequest req) {
		String dpt_id=req.getParameter("dpt_id");
		String delete=null;
		DepartmentDto dptdto = null;
		List<DepartmentDto> listdto = null;
		String uid = (String) ses.getAttribute("uid");
		String hid = (String) ses.getAttribute("hid");

		// copy cmd to dto
		HospitalDto dto;
		dto = new HospitalDto();
		dptdto = new DepartmentDto();
		dptdto.setHid(hid);	
		BeanUtils.copyProperties(departmentcmd, dptdto);
		//use service
		dto = hservice.featchHospitalInfo(hid);
		delete=dptService.removeDept(dpt_id);
	//	listdto = dptService.featchAllDepartment();
	
		System.out.println("delete");
		map.put("uid", uid);
		map.put("delete", delete);
		return "redirect:/home";
	}
	
	// get Departments
	@ModelAttribute("department")
	public Map<String, Object> getDepartment(HttpServletRequest req) {
		Map<String, Object> dptlist = new HashMap<String, Object>();
		List<String> name = new ArrayList<>();
		List<String> location = new ArrayList<>();
		ses = req.getSession();
		String hid = null;
		String dptname = null, dptlocation = null;
		List<DepartmentDto> listdto = new ArrayList<>();
		hid = (String) ses.getAttribute("hid");
		listdto = dptService.featchAllDepartment(hid);
		for (DepartmentDto dto : listdto) {
			dptname = dto.getDpt_name();
			dptlocation = dto.getDpt_location();
			name.add(dptname);
			location.add(dptlocation);
		}
		dptlist.put("name", name);
		dptlist.put("location", location);
		return dptlist;
	}
	
	
	
	@RequestMapping(value = "edit-hospital", method = RequestMethod.GET)
	public String editHome(Map<String, Object> map, @ModelAttribute("HospitalCmd") HospitalCommand HospitalCmd,
			HttpServletRequest req, HttpServletResponse res) {
		HospitalDto hdto=null;
		String hid=null;
		
		hid=req.getParameter("hid");
		
		//copy dto to cmd
		hdto=new HospitalDto();
		hdto=hservice.featchRecordBYId(hid);
		
		BeanUtils.copyProperties(hdto, HospitalCmd);
		map.put("HospitalCmd", HospitalCmd);
		
		return "edit-hospital";
		
	}
	
	
	
	@RequestMapping(value = "edit-hospital", method = RequestMethod.POST)
	public String modifyHospital(Map<String, Object> map, @ModelAttribute("HospitalCmd") HospitalCommand HospitalCmd,
			HttpServletRequest req, HttpServletResponse res) {
		HospitalDto hdto=null;
		String modify=null;
		
		MultipartFile logo = null;
		InputStream is = null;
		OutputStream os = null;
		String filename = null;

		// Get Name of file
		logo =HospitalCmd.getLogo_photo();
		filename = logo.getOriginalFilename();
		String fname = String.valueOf(CustomIdGenerator.getID());
		String ext = FilenameUtils.getExtension(filename);
		String filename2 = "logo-" + fname + "." + ext;

		try {

			String fileName3 = null;
			fileName3 = req.getSession().getServletContext().getRealPath("/");
			String imgPath = "/assests/images/hospital/";
			// File file=new
			// File("D:\\Hospital-Admin\\Hospital-Admin\\HospitalAdmin\\src\\main\\webapp\\assets\\images\\hospital\\");
			File file = new File("D:\\projects\\Hospital-Admin\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\HospitalAdmin\\assets\\images\\hospital\\");

			System.out.println(file.getAbsolutePath());
			os = new FileOutputStream(file + "\\" + filename2);

			is = logo.getInputStream();

			// perform file copy operation
			IOUtils.copy(is, os);

		} 
		
		
		catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		finally {
			// close streams
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e2) {
				
				e2.printStackTrace();
			}
			
			try {
				
				if (is != null) {
					is.close();
				}
				
			} 
			catch (IOException e2) {
				e2.printStackTrace();
			}
			
		}

		
		
		//copy cmd to dto
		hdto=new HospitalDto();
		BeanUtils.copyProperties(HospitalCmd, hdto);
		hdto.setLogo(filename2);
		//use  servie
		System.out.println("odify::::::::"+hdto.getAddress());
		modify=hservice.modifyHospital(hdto);

		
		map.put("modify",modify);
		
		return "redirect:/home";
		
	}


}
