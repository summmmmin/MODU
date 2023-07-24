package com.modu.app.prj.pay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
	
	// 결제성공
	@GetMapping("/success")
	public RedirectView successPay(@RequestParam("pg_token") String pgToken){
		PayVO kakaoApprove = payService.approveResponse(pgToken);
		ResponseEntity<PayVO> res = new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
		payService.insertPay(kakaoApprove);
		
		return new RedirectView("/modu/billing/Success");
	}
	
	// 결제 진행 중 취소
	@GetMapping("/cancel")
	public void cancel() {
		System.out.println("결제 진행 중 취소");
	}
	
	// 결제 실패
	@GetMapping("/fail")
    public void fail() {
    	System.out.println("결제 실패");
    }
	
	// 정기결제요청
	//@PostMapping("/subscript")
	
}
