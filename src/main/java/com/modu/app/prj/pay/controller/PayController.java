package com.modu.app.prj.pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.pay.service.PayService;
import com.modu.app.prj.prj.service.PrjService;

//2023-07-22 하수민 프로젝트 결제
@Controller
public class PayController {
	@Autowired
	PayService payService;
	@Autowired
	PrjService prjService;
	
	//프로젝트관리페이지-결제관리
	@GetMapping("billing")
	public String billing(String prjNo, Model model) {	
		// 프로젝트 정보
		model.addAttribute("prj", prjService.getPrjInfo(prjNo));

		return "prj/billing";
	}
	
	// 결제완료페이지
	@GetMapping("billing/Success")
	public String billSuccess() {
		return "prj/success";
	}
}
