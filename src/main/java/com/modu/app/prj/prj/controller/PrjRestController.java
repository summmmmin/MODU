package com.modu.app.prj.prj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.user.service.UserVO;

@RestController
@RequestMapping("manage/")
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
	
	// 프로젝트 이름 변경
	@PostMapping("updatePrjNm")
	public int updatePrjNm(PrjVO vo) {
		return prjService.setPrjNm(vo);
	}
	
	// 프로젝트 삭제
	@DeleteMapping("delPrj")
	public int deletePrj(String prjNo) {
		return prjService.delPrj(prjNo);
	}
	
	// 프로젝트 참여 회원 등급변경
	@PostMapping("updateGrade")
	public String updateGrade(@RequestBody PrjVO vo, HttpServletRequest request) {
		PrjVO info = new PrjVO();
		if(vo.getGrd().equals("G03")) {
			info.setCd("나무");
			info.setPrjUniNo(vo.getPrjUniNo());
			// 등급이 '나무'인 사람 찾기
			info = prjService.getMemInfo(info);
			info.setGrd("G02");
			prjService.setGrade(info);
			
			prjService.setGrade(vo);
		}else {
			prjService.setGrade(vo);
		}
		// 변경 후 사용자의 등급이 관리자가 아니면 프로젝트 리스트로
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		info = new PrjVO();
		info.setCd(null);
		info.setMembUniNo(user.getMembUniNo());
		info.setPrjUniNo(vo.getPrjUniNo());
		info = prjService.getMemInfo(info);
		//System.out.println(info);
		if(info.getCd().equals("나무") || info.getCd().equals("농부")) {
			return "true";
		}else {
			return "false";
		}
	}
	
	// 프로젝트 회원 탈퇴
	@PostMapping("kickMemb")
	public String kickPrjParti(@RequestBody PrjVO vo, HttpServletRequest request) {
		prjService.kickPrjParti(vo);
		
		// 변경 후 사용자의 등급이 관리자가 아니면 프로젝트 리스트로
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		PrjVO info = new PrjVO();
		info.setMembUniNo(user.getMembUniNo());
		info.setPrjUniNo(vo.getPrjUniNo());
		info = prjService.getMemInfo(info);
		if(info == null) {
			return "false";
		}
		else if(info.getCd().equals("나무") || info.getCd().equals("농부")) {
			return "true";
		}else {
			return "false";
		}
	}
	
	@GetMapping("prjNmUp")
	public String prjNm(@RequestBody PrjVO vo) {
		return "false";
	}
}
