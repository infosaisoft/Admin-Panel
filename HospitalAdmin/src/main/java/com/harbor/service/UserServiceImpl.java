package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.harbor.bo.UserBo;
import com.harbor.common.CustomIdGenerator;
import com.harbor.dao.UserDao;
import com.harbor.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userdao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public List<UserDto> getUser(long hid) {

		List<UserBo> listuserbo = null;
		List<UserDto> listuserdto = new ArrayList<>();

		// use dao
		listuserbo = userdao.getUser(hid);
		listuserbo.forEach(userbo -> {

			UserDto dto = new UserDto();

			BeanUtils.copyProperties(userbo, dto);
			listuserdto.add(dto);
		});

		return listuserdto;
	}

	@Override
	public String insertUser(UserDto userdto) {
		int count = 0;
		String password = null;
		UserBo userbo=null;
		
		

		// copy dto to bo

		userbo = new UserBo();
		BeanUtils.copyProperties(userdto, userbo);
		userbo.setPassword(encoder.encode(userbo.getPassword()));

		// use DAO

		count = userdao.insertUser(userbo);
		if (count == 0) {
			return "failed";
		}
		return "success";
	}

	@Override
	public String removeUser(long admin_id) {
		int count = 0;

		// use dao
		count = userdao.deleteUsert(admin_id);

		if (count == 0) {

			return "not delete";
		}

		return "delete";
	}

	@Override
	public int getpageCount(int pagesize) {
		long recordcount = 0;
		int pagecount = 0;
		recordcount = userdao.totalRecordsCount();
		pagecount = (int) (recordcount / pagesize);

		if (recordcount % pagecount != 0) {
			pagecount++;

			return pagecount;
		}
		return pagecount;
	}

	@Override
	public List<UserDto> getAllUser(int pageno, int pagesize) {
		int startpos = 0;
		List<UserDto> listdto = new ArrayList<>();
		List<UserBo> listbo = null;

		// start postion
		startpos = (pageno * pagesize) - pagesize;
		listbo = userdao.reportdata(startpos, pagesize);

		// covert listdomain to listdto

		listbo.forEach(bo -> {
			UserDto userdto = new UserDto();
			BeanUtils.copyProperties(bo, userdto);
			listdto.add(userdto);
		});
		return listdto;
	}

	@Override
	public UserDto getUserByID(long admin_id) {
		UserDto userdto = null;
		UserBo userbo = null;

		// use dao
		userbo = userdao.getUserboById(admin_id);

		// copy userbo to user dto
		userdto = new UserDto();
		BeanUtils.copyProperties(userbo, userdto);
		return userdto;
	}

	@Override
	public String modifyUserDetalis(UserDto dto) {
		UserBo bo=null;
            int count=0;
		// copy dto to bo
		bo=new UserBo();
		BeanUtils.copyProperties(dto, bo);
		
		//use dao
		count=userdao.updateUserBoById(bo);
		if(count==0) {
			
			return "modify not done";
		}
		return "modify done";
	}
}
