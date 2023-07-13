package com.modu.app.prj.user.impl;

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
	public UserVO loginCheck(String id) {
		return userMapper.loginCheck(id);
	}
}
