package com.modu.app.prj.user.impl;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.modu.app.prj.user.mapper.UserMapper;
import com.modu.app.prj.user.service.PrincipalDetails;
import com.modu.app.prj.user.service.UserService;
import com.modu.app.prj.user.service.UserVO;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	SqlSession sqlsession;

	@Autowired
	UserMapper userMapper;

	@Autowired
	public UserServiceImpl(SqlSession sqlsession) {
		super();
		this.sqlsession = sqlsession;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    UserVO userVO = userMapper.loginCheck(username);

	    if (userVO == null) {
	        System.out.println("유저정보 없음");
	        throw new UsernameNotFoundException("no user");
	    }
	    return new PrincipalDetails(userVO);
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
			return result.getId();
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

	@Override
	public int phNoVaild(String phNo) {
		return userMapper.phNoVaild(phNo);
	}

	@Override
	public String updateEmailAuthStatus(String token) {
		int result = userMapper.updateEmailAuthStatus(token);

		if (result > 0) {
			return "인증완료";
		} else {
			return "오류 또는 실패";
		}
	}

	@Override
	public String updateNm(Map<String, String> params) {
		int result = userMapper.updateNm(params);
		if (result > 0) {
			return "이름 변경 성공";
		} else {
			return "이름 변경 실패";
		}
	}

	@Override
	public String updatePwd(Map<String, String> params) {
		int result = userMapper.updatePwd(params);
		if (result > 0) {
			return "비밀번호 변경 성공";
		} else {
			return "비밀번호 변경 실패";
		}
	}

	@Override
	public String updateId(Map<String, String> params) {
		int result = userMapper.updateId(params);
		if (result > 0) {
			return "아이디 변경 성공";
		} else {
			return "아이디 변경 실패";
		}
	}

	@Override
	public int quitUser(String id) {
		int result = userMapper.quitUser(id);
		if (result == 1) {
			return result;
		} else {
			throw new RuntimeException("유저탈퇴실패");
		}
	}

	@Override
	public String updatePhone(Map<String, String> params) {
		int result = userMapper.updatePhone(params);
		if (result > 0) {
			return "휴대폰 번호 변경 성공"; // 오타나면 받는값이 달라서 서버에선 변경되더라도 클라이언트에선 오류 남
										// return ResponseEntity.ok("휴대폰 번호 변경 성공"); 이대로 받아오기
		} else {
			return "휴대폰 번호 변경 실패";
		}
	}

	@Override
	public String emailCode() {
		int codeLength = 6;
		String validChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();

		StringBuilder code = new StringBuilder();
		for (int i = 0; i < codeLength; i++) {
			int index = random.nextInt(validChars.length());
			String randomChar = validChars.substring(index, index + 1);
			code.append(randomChar);
		}

		return code.toString();
	}
	
	@Override
	public List<UserVO> userList() {
		return userMapper.userList();
	}

}
