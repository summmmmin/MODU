package com.modu.app.prj.login.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.login.mapper.LoginMapper;
import com.modu.app.prj.login.service.LoginService;
import com.modu.app.prj.login.service.LoginVO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper loginMapper;
	
	
	
	@Override
	public LoginVO login(LoginVO vo) {
		return loginMapper.login(vo);
	}
	
}
