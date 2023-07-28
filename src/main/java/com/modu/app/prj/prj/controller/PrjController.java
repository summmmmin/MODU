package com.modu.app.prj.prj.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.pay.service.PayService;
import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.user.service.UserVO;

// 2023-07-20 하수민 프로젝트 관리
@Controller
public class PrjController {
	
	@Autowired
	PrjService prjService;
	@Autowired
	PayService payService;
	
	//프로젝트 생성 페이지 이동
	@GetMapping("prjInsert")
	public String prjInsertForm(Model model) {
		model.addAttribute("prjVO", new PrjVO());
		return "prj/prjInsert";
	}
	
	// 프로젝트 생성
	@PostMapping("prjInsert")
	public String prjInsert(PrjVO prjVO, HttpSession session) {
		UserVO vo = (UserVO) session.getAttribute("user");
		prjVO.setMembUniNo(vo.getMembUniNo());
		prjService.insertPrj(prjVO);
		return "redirect:prjList";
	}
	
	// 프로젝트 리스트 페이지
	@GetMapping("prjList")
	public String prjList(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO vo = (UserVO) session.getAttribute("user");
		model.addAttribute("prjList",prjService.getPrjList(vo.getMembUniNo()));
		return "prj/prjList";
	}

	// 프로젝트 관리(프로젝트 참여회원)
	@GetMapping("prjManage")
	public String prjList(Model model, PrjVO prjVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		//로그인한 사람의 프로젝트 내 등급 확인(프로젝트  관리페이지는 관리자와 생성자만)
		PrjVO info = new PrjVO();
		info.setMembUniNo(user.getMembUniNo());
		info.setPrjUniNo(prjVO.getPrjUniNo());
		info = prjService.getMemInfo(info);
		System.out.println(info);
		if(info == null) {
			return "redirect:prjList";
		}else {
			if(info.getCd().equals("나무") || info.getCd().equals("농부")) {
			//	model.addAttribute("memb", prjService.getPrjPartiList(prjVO));
				model.addAttribute("prjNo", prjVO.getPrjUniNo());
				return "prj/프로젝트 관리";			
			}else {
				return "redirect:prjList";
			}			
		}
	}
	
	//프로젝트관리페이지-팀원관리
	@GetMapping("prjManage2")
	public String prjList2(PrjVO prjVO, Model model) {
		model.addAttribute("prjNo", prjVO.getPrjUniNo());
		return "prj/프로젝트 관리22";			
	}
	
	//프로젝트관리페이지-프로젝트 관리
	@GetMapping("prjUpdate")
	public String prjUpdate(Model model, PrjVO prjVO) {		
		return "prj/프로젝트 관리33";
	}
	
	//프로젝트관리페이지-결제관리
	@GetMapping("prjPay")
	public String prjPay(PrjVO prjVO, Model model) {	
		// 프로젝트 관련 정보 prjVO에 담기
		prjVO = prjService.getPrjInfo(prjVO.getPrjUniNo());
		model.addAttribute("prj", prjVO);
		
		// 프로젝트 결제 내역
		model.addAttribute("payList", payService.prjPayList(prjVO.getPrjUniNo()));
		System.out.println(payService.prjPayList(prjVO.getPrjUniNo()));
		return "prj/프로젝트 관리4";
	}
	
	// 프로젝트 관리 - 대시보드
	@GetMapping("prjDash")
	public String prjDash(String prjUniNo, Model model) {
		model.addAttribute("memCnt", prjService.getPrjMemCnt(prjUniNo));
		model.addAttribute("brdCnt", prjService.getBrdCnt(prjUniNo));
		model.addAttribute("chatrCnt", prjService.getChatrCnt(prjUniNo));
		model.addAttribute("prjNo", prjUniNo);
		return "prj/프로젝트 관리3";
	}
	
	// 프로젝트 초대
	// 초대하기 누르면
	// 이메일로 링크 발송 (초대토큰, 무슨프로젝트, 등급?, 사용한 토큰여부) insert
	
	// 링크로 들어왔을때
	// 토큰 저장, 로그인/회원가입 여부 확인 -> 로그인 회원가입
	// 토큰 있으면 프로젝트에 초대시키기(insert, update사용한 토큰으로)
	// 
	
	

}
