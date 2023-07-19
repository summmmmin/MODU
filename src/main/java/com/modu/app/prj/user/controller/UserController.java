package com.modu.app.prj.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.modu.app.prj.user.service.UserService;
import com.modu.app.prj.user.service.UserVO;
import com.modu.app.sms.service.MessageDTO;
import com.modu.app.sms.service.SmsResponseDTO;
import com.modu.app.sms.service.SmsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	@Autowired
	UserService userService;

	private SmsService smsService;

	@PostMapping("sms")
	@ResponseBody
	public SmsResponseDTO sendSms(@RequestBody MessageDTO messageDTO)
			throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException,
			NoSuchAlgorithmException, UnsupportedEncodingException {
		SmsResponseDTO response = smsService.sendSms(messageDTO);
		return response;
	}

	// 회원가입 사이트 이동
	@GetMapping("signup")
	public String signForm() {
		return "user/singup";
	}

//	@PostMapping("signup")
//	public String signup(UserVO userVO, Model model) {
//		
//
//	}

}
