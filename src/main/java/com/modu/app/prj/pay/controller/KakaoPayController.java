package com.modu.app.prj.pay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.pay.service.PayService;
import com.modu.app.prj.pay.service.PayVO;
import com.modu.app.prj.user.service.UserVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("payment/")
@RequiredArgsConstructor
public class KakaoPayController {
	@Autowired
	PayService payService;
	
	// 결제요청
	@PostMapping("/ready")
	public PayVO readyPay(@RequestBody PayVO payVO, HttpSession session) {
		// 로그인한사람 아이디
		String userId = ((UserVO) session.getAttribute("user")).getMembUniNo();
		payVO.setMembUniNo(userId);
		return payService.kakaoPayReady(payVO);
	}	
}
