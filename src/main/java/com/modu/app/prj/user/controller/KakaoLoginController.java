package com.modu.app.prj.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.user.service.KakaoToken;
import com.modu.app.prj.user.service.UserService;

@RestController
public class KakaoLoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	KakaoToken kakaoToken;

	
	@RequestMapping(value="/kakaologin")
	public String kakao() {
		return "kakaologin";
	}
	
	@RequestMapping(value="/kakaologindone")
	public String login(@RequestParam("code") String code, HttpSession session) {
		System.out.println("code: " + code);
		String access_token = kakaoToken.getKaKaoAccessToken(code);
		System.out.println("access_token" + access_token);
		HashMap<String,Object> userInfo = kakaoToken.getUserInfo(access_token);
		session.setAttribute("userId", userInfo.get("id"));
		session.setAttribute("name", userInfo.get("name"));
		System.out.println(userInfo);
		return "redirect:/";
	}

}
