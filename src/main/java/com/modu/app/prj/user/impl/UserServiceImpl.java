package com.modu.app.prj.user.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.modu.app.prj.user.mapper.UserMapper;
import com.modu.app.prj.user.service.UserService;
import com.modu.app.prj.user.service.UserVO;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO = userMapper.loginCheck(username);
		
		if(userVO == null) {
			System.out.println("유저정보 없음");
			throw new UsernameNotFoundException("no user");
		}
		return userVO;
	}
	@Override
	public String generateRandomToken() {
        String token = UUID.randomUUID().toString();
        return token;
	}
	@Override
	public int signup(UserVO userVO) {
		return userMapper.signup(userVO);
	}
	
	@Override
	public String idSearch(UserVO userVO) {
	    UserVO result = userMapper.idSearch(userVO);
	    if (result == null) {
	        return null;
	    } else {
	        return result.getId(); // 아이디가 존재할 때 아이디 값을 반환
	    }
	}

	
	@Override
	public int pwdSearch(UserVO userVO) {
		return userMapper.pwdSearch(userVO);
	}
	
	@Override
	public int idVaild(String id) {
	    return userMapper.idVaild(id);
	}
	
	@Override
	public UserVO emailAuth(String id) {
		return userMapper.emailAuth(id);
	}


}
