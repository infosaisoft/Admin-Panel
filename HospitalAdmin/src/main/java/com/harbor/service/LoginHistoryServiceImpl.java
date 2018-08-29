package com.harbor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harbor.bo.LoginHistoryBo;
import com.harbor.dao.LoginHistoryDao;
import com.harbor.dto.LoginHistoryDto;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

	@Autowired
	LoginHistoryDao hisdao;

	@Override
	public List<LoginHistoryDto> fetchLoginHistory(String admin_id) {

		List<LoginHistoryDto> listdto = new ArrayList<>();
		List<LoginHistoryBo> listbo = null;

		// use dao
		listbo = hisdao.getLoginHistory(admin_id);

		listbo.forEach(bo -> {

			LoginHistoryDto hisdto = new LoginHistoryDto();

			BeanUtils.copyProperties(bo, hisdto);

			listdto.add(hisdto);

		});
		return listdto;
	}

}
