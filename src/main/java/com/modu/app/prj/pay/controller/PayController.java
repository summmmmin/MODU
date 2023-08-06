package com.modu.app.prj.pay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.modu.app.prj.pay.service.PayService;
import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.user.service.UserVO;

//2023-07-22 하수민 프로젝트 결제
@Controller
public class PayController {
	@Autowired
	PayService payService;
	@Autowired
	PrjService prjService;
	
	//프로젝트관리페이지-결제관리
	@GetMapping("billing")
	public String billing(String prjNo, Model model, HttpSession session) {	
		UserVO user = (UserVO) session.getAttribute("user");
		//로그인한 사람의 프로젝트 내 등급 확인(프로젝트  관리페이지는 관리자와 생성자만)
		PrjVO info = new PrjVO();
		info.setMembUniNo(user.getMembUniNo());
		info.setPrjUniNo(prjNo);
		info = prjService.getMemInfo(info);
		if(info == null) {
			return "redirect:prjList";
		}else {
			if(info.getCd().equals("나무") || info.getCd().equals("농부")) {
				// 프로젝트 정보
				model.addAttribute("prj", prjService.getPrjInfo(prjNo));
				return "prj/billing";	
			}else {
				return "redirect:prjList";
			}			
		}
		
	}
	
}
