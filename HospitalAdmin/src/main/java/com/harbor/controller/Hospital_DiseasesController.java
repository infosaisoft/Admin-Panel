package com.harbor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.harbor.command.Hospital_Diseases_Command;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dto.Hospital_Diseases_Dto;
import com.harbor.service.Hospital_DiseasesService;

@Controller
@SessionAttributes({ "hid", "uid" })
@Scope("session")
public class Hospital_DiseasesController {

	@Autowired
	Hospital_DiseasesService service;

	HttpSession ses = null;

	@RequestMapping(value = "/addhospital_diseases", method = RequestMethod.GET)
	public String showpage(Map<String, Object> map,
			@ModelAttribute("hospital_diseases") Hospital_Diseases_Command hospital_diseases, HttpServletRequest req,
			HttpServletResponse res) {

		ses = req.getSession();

		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");

		map.put("uid", uid);
		map.put("hid", hid);

		return "addhospital_diseases";
	}

	@RequestMapping(value = "/addhospital_diseases", method = RequestMethod.POST)
	public String registration(Map<String, Object> map,
			@ModelAttribute("hospital_diseases") Hospital_Diseases_Command hospital_diseases, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		ses = req.getSession();

		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");
		System.out.println(hid);

		map.put("uid", uid);
		map.put("hid", hid);

		Hospital_Diseases_Dto dto = null;
		String result = null;

		MultipartFile image = null, video = null, document = null;
		String fname1 = null, fname2 = null;
		String fname3 = null, fname4 = null;
		String fname5 = null, fname6 = null;
		/*
		 * Object objid = Image_videoCount.getID(); String objidstr = objid.toString();
		 */
		OutputStream os1 = null, os2 = null, os3 = null;
		InputStream is1 = null, is2 = null, is3 =null;

		// get the names of the uploaded files
		image = hospital_diseases.getImages();
		video = hospital_diseases.getVideos();
		document = hospital_diseases.getDocuments();

		// get name of the file from upload
		fname1 = image.getOriginalFilename();
		String id = String.valueOf(CustomIdGenerator.getID());
		String Extenstion = FilenameUtils.getExtension(fname1);
		fname3 = "img-" + id + "." + Extenstion;

		fname2 = video.getOriginalFilename();
		String Extenstion1 = FilenameUtils.getExtension(fname2);
		fname4 = "vid-" + id + "." + Extenstion1;
		
		fname5 = document.getOriginalFilename();
		String Extension2 = FilenameUtils.getExtension(fname5);
		fname6 = "doc-" + id + "." + Extension2;

		try {


			File imageFile1 = new File(req.getServletContext().getRealPath("/assets/uploads/disease/images/"), fname3);
			File imageFile2 = new File(req.getServletContext().getRealPath("/assets/uploads/disease/videos/"), fname4);
			File imageFile3 = new File(req.getServletContext().getRealPath("/assets/uploads/disease/documents/"), fname6);
			// creating input stream representing upload file
			is1 = image.getInputStream();
			is2 = video.getInputStream();
			is3 = document.getInputStream();

			// create output stream pointing to dest file on server matchine file system
			os1 = new FileOutputStream( imageFile1);
			os2 = new FileOutputStream( imageFile2);
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
		dto = new Hospital_Diseases_Dto();

	
		BeanUtils.copyProperties(hospital_diseases, dto);
		dto.setHid(hid);
		dto.setImages(fname3);
		dto.setVideos(fname4);
		dto.setDocuments(fname6);

		result = service.registrationHospital_Diseases(dto);
		map.put("result", result);
		return "addhospital_diseases";
	}

	@RequestMapping(value = "/getall_hospital_diseases", method = RequestMethod.GET)
	public String getAllHospital_diseases(Map<String, Object> map) {
		List<Hospital_Diseases_Dto> listdto = null;
		listdto = service.fetchall();
		map.put("listdto", listdto);

		return "list_all_Hospital_diseases";
	}

	@RequestMapping(value = "/edit_hospital_diseases", method = RequestMethod.GET)
	public String editHospital_diseases(Map<String, Object> map,
			@ModelAttribute("edithospital_diseases") Hospital_Diseases_Command editHospital_Diseases,
			HttpServletRequest req, HttpServletResponse res) {

		HttpSession ses = null;
		ses = req.getSession();
		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");

		map.put("uid", uid);
		map.put("hid", hid);

		Hospital_Diseases_Dto dto = null;
		// copy cmd to dto
		dto = new Hospital_Diseases_Dto();
		BeanUtils.copyProperties(editHospital_Diseases, dto);
		long sno = Long.parseLong(req.getParameter("sno"));
		System.out.println("controller::" + sno);
		dto = service.fetchbysno(sno);
		map.put("dto", dto);
		return "edit_hospital_diseases";

	}

	@RequestMapping(value = "/edit_hospital_diseases", method = RequestMethod.POST)
	public String processEdit(Map<String, Object> map,
			@ModelAttribute("edithospital_diseases") Hospital_Diseases_Command editHospital_Diseases,
			HttpServletRequest req, HttpServletResponse res) {

		HttpSession ses = null;
		ses = req.getSession();
		long uid = (long) ses.getAttribute("uid");
		long hid = (long) ses.getAttribute("hid");

		map.put("uid", uid);
		map.put("hid", hid);

		String updatemsg = null;
		List<Hospital_Diseases_Dto> listdto = null;
		Hospital_Diseases_Dto dto = null;
		
		MultipartFile image = null, video = null, document = null;
		String fname1 = null, fname2 = null;
		String fname3 = null, fname4 = null;
		String fname5 = null, fname6 = null;
		/*
		 * Object objid = Image_videoCount.getID(); String objidstr = objid.toString();
		 */
		OutputStream os1 = null, os2 = null, os3 = null;
		InputStream is1 = null, is2 = null, is3 =null;

		// get the names of the uploaded files
		image = editHospital_Diseases.getImages();
		video = editHospital_Diseases.getVideos();
		document = editHospital_Diseases.getDocuments();

		// get name of the file from upload
		fname1 = image.getOriginalFilename();
		String id = String.valueOf(CustomIdGenerator.getID());
		String Extenstion = FilenameUtils.getExtension(fname1);
		fname3 = "img-" + id + "." + Extenstion;

		fname2 = video.getOriginalFilename();
		String Extenstion1 = FilenameUtils.getExtension(fname2);
		fname4 = "vid-" + id + "." + Extenstion1;
		
		fname5 = document.getOriginalFilename();
		String Extension2 = FilenameUtils.getExtension(fname5);
		fname6 = "doc-" + id + "." + Extension2;

		try {

			File imageFile1 = new File(req.getServletContext().getRealPath("/assets/uploads/disease/images/"), fname3);
			File imageFile2 = new File(req.getServletContext().getRealPath("/assets/uploads/disease/videos/"), fname4);
			File imageFile3 = new File(req.getServletContext().getRealPath("/assets/uploads/disease/documents/"), fname6);
			// creating input stream representing upload file
			is1 = image.getInputStream();
			is2 = video.getInputStream();
			is3 = document.getInputStream();

			// create output stream pointing to dest file on server matchine file system
			os1 = new FileOutputStream( imageFile1);
			os2 = new FileOutputStream( imageFile2);
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
		dto = new Hospital_Diseases_Dto();

		dto.setImages(fname3);
		dto.setVideos(fname4);
		dto.setHid(hid);
		dto.setImages(fname3);
		dto.setVideos(fname4);
		dto.setDocuments(fname6);
		// copuy cmd to dto
		dto = new Hospital_Diseases_Dto();
		BeanUtils.copyProperties(editHospital_Diseases, dto);
		// use service
		updatemsg = service.modifyHospital_Diseases(dto);
		listdto = service.fetchall();
		map.put("listdto", listdto);
		map.put("updatemsg", updatemsg);
		return "list_all_Hospital_diseases";

	}

	@RequestMapping(value = "/delete_hospital_diseases", method = RequestMethod.GET)
	public String delleteHospital(HttpServletRequest req, Map<String, Object> map) {

		String delete = null;
		List<Hospital_Diseases_Dto> listdto = null;
		long sno =Long.parseLong((req.getParameter("sno")));
		// use service
		delete = service.removeHospital_Diseases(sno);
		listdto = service.fetchall();
		map.put("listdto", listdto);
		map.put("delete", delete);
		return "list_all_Hospital_diseases";
	}

}
