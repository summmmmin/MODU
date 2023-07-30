package com.modu.app.prj.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.user.service.KakaoToken;
import com.modu.app.prj.user.service.UserService;

@RestController
public class KakaoLoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	KakaoToken kakaoToken;

	@ResponseBody
	@GetMapping("oauth/kakao")	//그냥 sout출력용 주소, 실제로는 없는 주소가 맞음
	public void kakaoCallback(@RequestParam String code) {
		String access_Token = kakaoToken.getKaKaoAccessToken(code);
		System.out.println("카카오 토큰 발급 : " + access_Token);

		HashMap<String, Object> userInfo = kakaoToken.getUserInfo(access_Token);
		System.out.println("유저 정보 : " + userInfo);
	}
}

