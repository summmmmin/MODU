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
		System.out.println("----------------------------------------------");
		System.out.println(username);
		UserVO userVO = userMapper.loginCheck(username);
		
		System.out.println(userVO);
		System.out.println("----------------------------------------------");
		
		if(userVO == null) {
			System.out.println("vo null !!!!!!!!!!!!!!!");
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
	public int idSearch(UserVO userVO) {
		return userMapper.idSearch(userVO);
	}
	
	@Override
	public int pwdSearch(UserVO userVO) {
		return userMapper.pwdSearch(userVO);
	}
	
	@Override
	public int idVaild(String id) {
	    return userMapper.idVaild(id);
	}


}
