package com.modu.app.prj.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.modu.app.prj.user.service.KakaoToken;
import com.modu.app.prj.user.service.UserService;
import com.modu.app.prj.user.service.UserVO;
import com.modu.app.sms.service.MessageDTO;
import com.modu.app.sms.service.SmsResponseDTO;
import com.modu.app.sms.service.SmsService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	KakaoToken kakaoToken;

	private final SmsService smsService;

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
		return "user/signup";
	}

	@PostMapping("signup")
	public String signup(UserVO userVO, Model model) {
		// 사용자가 입력한 비밀번호를 가져옴
		String rawPassword = userVO.getPassword();

		System.out.println("입력받은 비밀번호: " + rawPassword);

		// BCryptPasswordEncoder를 이용하여 비밀번호 암호화
		BCryptPasswordEncoder scpwd = new BCryptPasswordEncoder();
		String encryptedPassword = scpwd.encode(rawPassword);

		System.out.println("암호화된 비밀번호: " + encryptedPassword);

		userVO.setPassword(encryptedPassword);

		userService.signup(userVO);
		return "redirect:login";
	}

	@RestController
	@AllArgsConstructor
	@RequestMapping("oauth")
	public class OAuthController {

		@ResponseBody
		@GetMapping("kakao")
		public void kakaoCallback(@RequestParam String code) {
			System.out.println(code);
			String access_Token = kakaoToken.getKaKaoAccessToken(code);
			
			HashMap<String, Object> userInfo = kakaoToken.getUserInfo(access_Token);
			System.out.println("###access_Token#### : " + access_Token);
			System.out.println("###nickname#### : " + userInfo.get("nickname"));
			System.out.println("###email#### : " + userInfo.get("email"));
			System.out.println("카카오 토큰 발급 : " + access_Token);
			
		}
	}

}
