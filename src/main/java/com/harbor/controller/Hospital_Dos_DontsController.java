package com.harbor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
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
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.harbor.command.Hospital_Dos_DontsCommand;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dto.Hospital_Dos_DontsDto;
import com.harbor.service.Hospital_Dos_DontsService;

@Controller
@SessionAttributes({ "hid", "uid" })
@Scope("session")
public class Hospital_Dos_DontsController {

	@Autowired
	Hospital_Dos_DontsService service;

	HttpSession ses = null;

	@RequestMapping(value = "/addhospital_dos_donts", method = RequestMethod.GET)
	public String showpage(Map<String, Object> map,
			@ModelAttribute("hospital_dos_donts") Hospital_Dos_DontsCommand hospital_dos_donts, HttpServletRequest req,
			HttpServletResponse res) {

		ses = req.getSession();

		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");

		map.put("uid", uid);
		map.put("hid", hid);

		return "addhospital_dos_donts";
	}

	@RequestMapping(value = "/addhospital_dos_donts", method = RequestMethod.POST)
	public String registration(Map<String, Object> map,
			@ModelAttribute("hospital_dos_donts") Hospital_Dos_DontsCommand hospital_dos_donts, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		ses = req.getSession();
		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");

		

		map.put("uid", uid);
		map.put("hid", hid);

		Hospital_Dos_DontsDto dto = null;
		String result = null;

		MultipartFile image = null, video = null,document = null;;
		String fname1 = null, fname2 = null,fname5 =null;
		String fname3 = null, fname4 = null,fname6 =null;
		OutputStream os1 = null, os2 = null, os3 = null;
		InputStream is1 = null, is2 = null, is3 =null;

		// get the names of the uploaded files
		image = hospital_dos_donts.getImages();
		video = hospital_dos_donts.getVideos();
		document=hospital_dos_donts.getDocument();

		// get name of the file from upload
		fname1 = image.getOriginalFilename();
		String id = String.valueOf(CustomIdGenerator.getID());
		String Extenstion = FilenameUtils.getExtension(fname1);
		fname3 = "img-" + id + "." + Extenstion;

		fname2 = video.getOriginalFilename();
		String Extenstion1 = FilenameUtils.getExtension(fname2);
		fname4 = "vid-" + id + "." + Extenstion1;
		
		fname5= document.getOriginalFilename();
		String Extenstion2 = FilenameUtils.getExtension(fname5);
		fname6 = "Doc-" + id + "." + Extenstion2;
		
		try {

		
			

			File imageFile1 = new File(req.getServletContext().getRealPath("/assets/images/hospital/"), fname3);
			File imageFile2 = new File(req.getServletContext().getRealPath("/assets/images/hospital/"), fname4);
			File imageFile3 = new File(req.getServletContext().getRealPath("/assets/images/hospital/"), fname5);

			// creating input stream representing upload file
			is1 = image.getInputStream();
			is2 = video.getInputStream();
			is3=document.getInputStream();
			

			// create output stream pointing to dest file on server matchine file system
			os1 = new FileOutputStream(imageFile1);
			os2 = new FileOutputStream(imageFile2);
			os3 = new FileOutputStream(imageFile3);
			
			// perform copy operation to completing download
			IOUtils.copy(is1, os1);
			IOUtils.copy(is2, os2);
			IOUtils.copy(is3, os3);
			
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// close all stram
			try {
				if (os1 != null) {
					os1.close();
				}
			} catch (IOException io1) {
				io1.printStackTrace();
			}

			try {
				if (os2 != null) {
					os2.close();
				}
			} catch (IOException io2) {
				io2.printStackTrace();
			}
			
			try {
				if (os3 != null) {
					os3.close();
				}
			} catch (IOException io3) {
				io3.printStackTrace();
			}

			try {
				if (is1 != null) {
					is1.close();
				}
			} catch (IOException io1) {
				io1.printStackTrace();
			}
			try {
				if (is2 != null) {
					is2.close();
				}
			} catch (IOException io1) {
				io1.printStackTrace();
			}
			
			try {
				if (is3 != null) {
					is3.close();
				}
			} catch (IOException io3) {
				io3.printStackTrace();
			}

		}

		// copy cmd to dto
		dto = new Hospital_Dos_DontsDto();

		BeanUtils.copyProperties(hospital_dos_donts, dto);
		dto.setHid(hid);
		dto.setImages(fname3);
		dto.setVideos(fname4);
		dto.setDocument(fname6);
		

		result = service.registrationHospital_Dos_Donts(dto);
		map.put("result", result);
		return "addhospital_dos_donts";
	}

	@RequestMapping(value = "/getall_hospital_dos_donts", method = RequestMethod.GET)
	public String getAllHospital_Dos_Donts(Map<String, Object> map,HttpServletRequest req) {
		List<Hospital_Dos_DontsDto> listdto = null;
		  
		ses=req.getSession();
		listdto = service.fetchall();
		map.put("listdto", listdto);
		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");
       map.put("hid", hid);
		return "list_all_Hospital_dos_donts";
	}
	
	
   
	@RequestMapping(value = "/edit_hospital_dos_donts", method = RequestMethod.GET)
	public String editHospital_dos_Donts(Map<String, Object> map,
			@ModelAttribute("edithospital_dos_donts") Hospital_Dos_DontsCommand edithospital_dos_donts,HttpServletRequest req, HttpServletResponse res) {
		ses = req.getSession();
		long hid=0,uid=0;
		
      hid=(long) ses.getAttribute("hid");
      uid=(long) ses.getAttribute("uid");
		 
		map.put("uid", uid);
		map.put("hid", hid);

		Hospital_Dos_DontsDto dto = null;
		// copy cmd to dto
		dto = new Hospital_Dos_DontsDto();
		BeanUtils.copyProperties(edithospital_dos_donts, dto);
		long id = Long.parseLong(req.getParameter("sno"));
		dto = service.fetchbysno(id);
		map.put("dto", dto);
		return "edit_hospital_dos_donts";
		
		 
	}

	@RequestMapping(value = "/edit_hospital_dos_donts", method = RequestMethod.POST)
	public String processEdit(Map<String, Object> map,
			@ModelAttribute("edithospital_dos_donts") Hospital_Dos_DontsCommand edithospital_dos_donts,
			HttpServletRequest req, HttpServletResponse res,HttpSession ses) {
		Hospital_Dos_DontsDto dto=null;
		long hid=0;
		
		hid=(long) map.get("hid");
		 //hid= (long) ses.getAttribute("hid");

        
		map.put("hid", hid);
		
		

		MultipartFile image = null, video = null,document = null;;
		String fname1 = null, fname2 = null,fname5 =null;
		String fname3 = null, fname4 = null,fname6 =null;
		OutputStream os1 = null, os2 = null, os3 = null;
		InputStream is1 = null, is2 = null, is3 =null;

		// get the names of the uploaded files
		image = edithospital_dos_donts.getImages();
		video = edithospital_dos_donts.getVideos();
		document=edithospital_dos_donts.getDocument();

		// get name of the file from upload
		fname1 = image.getOriginalFilename();
		String id = String.valueOf(CustomIdGenerator.getID());
		String Extenstion = FilenameUtils.getExtension(fname1);
		fname3 = "img-" + id + "." + Extenstion;

		fname2 = video.getOriginalFilename();
		String Extenstion1 = FilenameUtils.getExtension(fname2);
		fname4 = "vid-" + id + "." + Extenstion1;
		
		fname5= document.getOriginalFilename();
		String Extenstion2 = FilenameUtils.getExtension(fname5);
		fname6 = "Doc-" + id + "." + Extenstion2;
		
		try {

		
			

			File imageFile1 = new File(req.getServletContext().getRealPath("/assets/images/hospital/"), fname3);
			File imageFile2 = new File(req.getServletContext().getRealPath("/assets/images/hospital/"), fname4);
			File imageFile3 = new File(req.getServletContext().getRealPath("/assets/images/hospital/"), fname5);

			// creating input stream representing upload file
			is1 = image.getInputStream();
			is2 = video.getInputStream();
			is3=document.getInputStream();
			

			// create output stream pointing to dest file on server matchine file system
			os1 = new FileOutputStream(imageFile1);
			os2 = new FileOutputStream(imageFile2);
			os3 = new FileOutputStream(imageFile3);
			
			// perform copy operation to completing download
			IOUtils.copy(is1, os1);
			IOUtils.copy(is2, os2);
			IOUtils.copy(is3, os3);
			
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			// close all stram
			try {
				if (os1 != null) {
					os1.close();
				}
			} catch (IOException io1) {
				io1.printStackTrace();
			}

			try {
				if (os2 != null) {
					os2.close();
				}
			} catch (IOException io2) {
				io2.printStackTrace();
			}
			
			try {
				if (os3 != null) {
					os3.close();
				}
			} catch (IOException io3) {
				io3.printStackTrace();
			}

			try {
				if (is1 != null) {
					is1.close();
				}
			} catch (IOException io1) {
				io1.printStackTrace();
			}
			try {
				if (is2 != null) {
					is2.close();
				}
			} catch (IOException io1) {
				io1.printStackTrace();
			}
			
			try {
				if (is3 != null) {
					is3.close();
				}
			} catch (IOException io3) {
				io3.printStackTrace();
			}

		}

		// copy cmd to dto
		dto = new Hospital_Dos_DontsDto();
		dto.setHid(hid);
		dto.setImages(fname3);
		dto.setVideos(fname4);
		dto.setDocument(fname6);
		

		

		String updatemsg = null;
		List<Hospital_Dos_DontsDto> listdto = null;
		// copuy cmd to dto
		BeanUtils.copyProperties(edithospital_dos_donts, dto);
		// use service
		updatemsg = service.modifyHospital_Dos_Donts(dto);
		listdto = service.fetchall();
		map.put("listdto", listdto);
		map.put("updatemsg", updatemsg);
		return "list_all_Hospital_dos_donts";

	}

	@RequestMapping(value = "/delete_hospital_dos_donts", method = RequestMethod.GET)
	public String delleteHospital(HttpServletRequest req, Map<String, Object> map) {
		String delete = null;
		List<Hospital_Dos_DontsDto> listdto = null;

		long id = Long.parseLong((req.getParameter("sno")));

		// use service

		delete = service.removeHospital_Dos_Donts(id);
		listdto = service.fetchall();
		map.put("listdto", listdto);
		map.put("delete", delete);
		return "list_all_Hospital_dos_donts";
	}

}
