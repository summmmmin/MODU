package com.modu.app.prj.prj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;

@RestController
public class PrjRestController {
	
	@Autowired
	PrjService prjService;
	
	// 프로젝트 참여 회원 리스트
	@GetMapping("prjParList")
	public List<PrjVO> prjList(PrjVO prjVO){
		return prjService.getPrjPartiList(prjVO);
	}
	
	// 등급 공통코드
	@GetMapping("grdCode")
	public List<PrjVO> grdList(){
		return prjService.grdCmmn();
	}
}
