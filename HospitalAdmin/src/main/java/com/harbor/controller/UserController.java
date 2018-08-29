package com.harbor.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.harbor.command.UserCommand;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dto.UserDto;
import com.harbor.service.UserService;

@Controller
@SessionAttributes({ "hid", "uid" })
@Scope("session")
public class UserController {

	@Autowired
	UserService userser;
	HttpSession ses = null;

	//get active url   
			@ModelAttribute("activeurl")
			public Map<String, Object> getActiveUrl(HttpServletRequest req) {
				Map<String, Object> activeurl = new HashMap<String, Object>();
				
				String url="user";
				
				activeurl.put("url", url);
				return activeurl;
			}
		
	@RequestMapping(value = "/manage-user", method = RequestMethod.GET)
	public String manageUser(HttpServletRequest req, Map<String, Object> map,
			@ModelAttribute("insert_user") UserCommand insert_user) {

		ses = req.getSession();
		String uid = (String) ses.getAttribute("uid");
		if (uid == null) {
			return "redirect:/login";
		}
		String hid = (String) ses.getAttribute("hid");

		List<UserDto> userdto = null;

		userdto = userser.getUser(hid);

		String current = System.getProperty("user.dir");
		System.out.println(current);
		map.put("userDto", userdto);
		map.put("hid", hid);
		return "manage-user";
	}

	@ModelAttribute("rolelist")
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

	/*
	 * @ModelAttribute("rolelist1") private Map<String, String>
	 * getRoles1(HttpServletRequest req) { Map<String, String> rolelist1 = new
	 * HashMap<String, String>(); String role=null; String
	 * admin_id=req.getParameter("admin_id"); UserDto
	 * dto=userser.getUserByID(admin_id); role=dto.getRole(); rolelist1.put("role",
	 * role); return rolelist1"; }
	 */

	@RequestMapping(value = "/manage-user", method = RequestMethod.POST)
	public String insertUser(HttpServletRequest req, Map<String, Object> map,
			@Valid @ModelAttribute("insert_user") UserCommand insert_user, BindingResult errors) {

		UserDto user = new UserDto();
		ses = req.getSession();
		ses.setAttribute("admin_id", user.getAdmin_id());
		String hid = (String) ses.getAttribute("hid");
		MultipartFile userPhoto = null;
		InputStream is = null;
		OutputStream os = null;
		String filename = null;

		// Get Name of file
		userPhoto = insert_user.getPhoto();
		filename = userPhoto.getOriginalFilename();
		String fname = String.valueOf(CustomIdGenerator.getID());
		String ext = FilenameUtils.getExtension(filename);
		String filename2 = "IMG-" + fname + "." + ext;

		try {

			String fileName3 = null;
			fileName3 = req.getSession().getServletContext().getRealPath("/");
			System.out.println("" + fileName3);
			String imgPath = "/assests/images/hospital/";
			// File file=new
			// File("D:\\Hospital-Admin\\Hospital-Admin\\HospitalAdmin\\src\\main\\webapp\\assets\\images\\hospital\\");
			File file = new File(
					"D:\\projects\\Hospital-Admin\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\HospitalAdmin\\assets\\images\\hospital\\");

			System.out.println(file.getAbsolutePath());
			os = new FileOutputStream(file + "\\" + filename2);

			is = userPhoto.getInputStream();

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
		user.setHid(hid);
		// copy cmd to dto
		BeanUtils.copyProperties(insert_user, user);

		user.setPhoto(filename2);

		// use Service
		String userResult = userser.insertUser(user);
		System.out.println(userResult);
		String admin_id = (String) ses.getAttribute("admin_id");
		System.out.println(admin_id);
		map.put("hid", hid);
		map.put("userResult", userResult);
		map.put("admin_id", admin_id);
		map.put("hid", hid);
		return "manage-user";

	}

	@RequestMapping(value = "delete_admin", method = RequestMethod.GET)
	public String deleteAdminId(HttpServletRequest req, Map<String, Object> map,
			@ModelAttribute("insert_user") UserCommand insert_user) {

		String admin_id = req.getParameter("admin_id");
		System.out.println("delete controller" + admin_id);
		ses = req.getSession();

		String hid = (String) ses.getAttribute("hid");
		String delete = null;
		delete = userser.removeUser(admin_id);
		List<UserDto> userdto = null;

		userdto = userser.getUser(hid);
		map.put("userDto", userdto);
		System.out.println(delete);
		map.put("delete", delete);
		return "redirect:/manage-user";
	}

	@RequestMapping(value = "edit_admin", method = RequestMethod.GET)
	public String editUser(Map<String, Object> map, @ModelAttribute("insert_user") UserCommand insert_user,
			HttpServletRequest req) {
		String admin_id;
		UserDto userdto = null;

		System.out.println("edit controller");
		admin_id = req.getParameter("admin_id");

		// use service
		userdto = userser.getUserByID(admin_id);
		// copy dto to cmd
		BeanUtils.copyProperties(userdto, insert_user);
		map.put("userdto", userdto);
		map.put("insert_user", insert_user);
		return "edit_user";
	}

	@RequestMapping(value = "edit_admin", method = RequestMethod.POST)
	public String modiyUser(Map<String, Object> map, @ModelAttribute("insert_user") UserCommand insert_user,
			HttpServletRequest req) {
		String modify = null;
		UserDto dto = null;
		
		
		MultipartFile userPhoto = null;
		InputStream is = null;
		OutputStream os = null;
		String filename = null;

		// Get Name of file
		userPhoto = insert_user.getPhoto();
		filename = userPhoto.getOriginalFilename();
		String fname = String.valueOf(CustomIdGenerator.getID());
		String ext = FilenameUtils.getExtension(filename);
		String filename2 = "IMG-" + fname + "." + ext;

		try {

			String fileName3 = null;
			fileName3 = req.getSession().getServletContext().getRealPath("/");
			System.out.println("" + fileName3);
			// File file=new
			// File("D:\\Hospital-Admin\\Hospital-Admin\\HospitalAdmin\\src\\main\\webapp\\assets\\images\\hospital\\");
			File file = new File(
					"D:\\projects\\Hospital-Admin\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\HospitalAdmin\\assets\\images\\hospital\\");
			System.out.println(file.getAbsolutePath());
			os = new FileOutputStream(file + "\\" + filename2);

			is = userPhoto.getInputStream();

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

		// copy cmd to dto
		dto = new UserDto();
		BeanUtils.copyProperties(insert_user, dto);
		dto.setPhoto(filename2);
		// use service
		modify = userser.modifyUserDetalis(dto);
		map.put("modify", modify);
		map.put("insert_user", insert_user);
		return "redirect:/manage-user";
	}

}
