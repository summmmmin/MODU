package com.modu.app.prj.prj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;
import com.modu.app.prj.user.service.UserVO;
// 2023-07-20 하수민 프로젝트 관리
@RestController
@RequestMapping("manage/")
public class PrjRestController {
	
	@Autowired
	PrjService prjService;
	
	@Autowired
	FileService fileService;
	
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
	public Map<String, Object> updatePrjNm(@RequestBody PrjVO vo) {

		Map<String, Object> map = new HashMap<>();
		int result = prjService.setPrjNm(vo);
		
		//변경 성공 여부
		if(result > 0) {
			map.put("retCode", "Success");
			map.put("data", vo);
		}else {
			map.put("retCode", "Fail");
		}
		return map;
	}
	
	// 프로젝트 삭제
	@DeleteMapping("delPrj")
	public int deletePrj(@RequestBody PrjVO vo) {
		String prjNo = vo.getPrjUniNo();	//프로젝트번호pk
		PrjVO chk = prjService.getPrjInfo(prjNo);
		String chkNm = chk.getPrjNm();	//원래 프로젝트명

		// 확인 문구가 프로젝트명과 일치할때만 delete문 실행 
		if(vo.getPrjNm().equals(chkNm)) {
			if(prjService.delPrj(prjNo) > 0) {
				// 삭제 성공
				return 1;
			}else {
				return 2;
			}
		}else {
			return 0;
		}
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
			// 원래 나무 등급인 사람 등급 새싹으로
			prjService.setGrade(info);
			// 새로운 나무 등급
			prjService.setGrade(vo);
		}else {
			prjService.setGrade(vo);
		}
		
		// 변경 후 사용자의 등급이 관리자가 아니면 프로젝트 리스트로
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		info = new PrjVO();
		info.setMembUniNo(user.getMembUniNo());
		info.setPrjUniNo(vo.getPrjUniNo());
		info = prjService.getMemInfo(info);
		if(info.getCd().equals("나무") || info.getCd().equals("농부")) {
			return "true";
		}else {
			return "false";
		}
	}
	
	// 프로젝트 회원 탈퇴
	@PostMapping("kickMemb")
	public String kickPrjParti(@RequestBody PrjVO vo, HttpSession session) {
		
		// 탈퇴 시 프로젝트 참여 멤버 테이블 참여여부 'N'으로 바꾸기
		prjService.kickPrjParti(vo);
		
		// 변경 후 사용자의 등급이 관리자가 아니면 프로젝트 리스트로
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
	
	
	@GetMapping("prjInfo")
	public PrjVO prjInfo(String prjNo) {
		PrjVO vo = prjService.getPrjInfo(prjNo);
		vo.setMemCnt(prjService.getPrjMemCnt(prjNo));
		return vo;
	}
	
	// 정기결제 해지/재시작
	@GetMapping("payCancel")
	public int payCancel(String prjNo) {
		//프로젝트 정보
		PrjVO vo = prjService.getPrjInfo(prjNo);

		// 프로젝트 구독상태
		String status = vo.getSubspYn();
		if(status.equals("Y")) {
			// 프로젝트 상태 구독중일때 해지 신청 상태로 변경(해지)
			vo.setSubspYn("C");
			if(prjService.setPrjSubsp(vo)>0) {
				return 1;		// 업데이트 성공
			}else {
				return 0;
			}
		}else if(status.equals("C")) {
			// 프로젝트 상태 구독중인데 해지 신청 상태일때 구독중으로 변경(재시작)
			vo.setSubspYn("Y");
			if(prjService.setPrjSubsp(vo)>0) {
				return 2;		// 업데이트 성공
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}
	
	@PostMapping("getPrjCnt")
	public List<PrjVO> prjcnt(@RequestBody PrjVO vo){
		return prjService.getPrjCnt(vo);
	}
	
	// 등급별 회원 수
	@GetMapping("getGrdCnt")
	public Map<String, Object> getGrdCnt(String prjNo){
		return prjService.getGrdCnt(prjNo);
	}
	
	// 프로젝트 참여자 정보
	@GetMapping("getParticiMembInfo")
	public PrjVO meminfo(String prjNo, HttpSession session) {
		PrjVO vo = new PrjVO();
		vo.setPrjUniNo(prjNo);
		vo.setMembUniNo(((UserVO) session.getAttribute("user")).getMembUniNo());
		// vo에 조회한 회원 정보 담기
		vo = prjService.getMemInfo(vo);
		return vo;
	}
	
	// 프로젝트 참여자 정보 수정
	@PostMapping("updateParticiMembInfo")
	public String updateMemInfo(String prjNo, @RequestBody PrjVO vo, HttpSession session) {
		if(vo.getParticiMembUniNo() == null) {
			PrjVO prj = new PrjVO();
			prj.setPrjUniNo(prjNo);
			prj.setMembUniNo(((UserVO) session.getAttribute("user")).getMembUniNo());
			// vo에 조회한 회원 정보 담기
			prj = prjService.getMemInfo(prj);
			vo.setParticiMembUniNo(prj.getParticiMembUniNo()); 			
		}
		System.out.println(vo);
		prjService.updateParticiInfo(vo);
		return "1";
	}
	
	private PrjVO getParticiInfo(String user, String prj) {
		PrjVO vo = new PrjVO();
		vo.setPrjUniNo(prj);
		vo.setMembUniNo(user);
		// vo에 조회한 회원 정보 담기
		vo = prjService.getMemInfo(vo);
		return vo;
	}
	
	//프로젝트탈퇴(개인)
	@GetMapping("quitMemb")
	public int quirtPrjParti(String prjNo, HttpSession session) {
		PrjVO vo = getParticiInfo(((UserVO) session.getAttribute("user")).getMembUniNo(), prjNo);
		return prjService.kickPrjParti(vo);
	}
	
	// 프사등록 
	@PostMapping("userImage")
	public String postInsert(@RequestParam("prjNo") String prjNo, HttpSession session, @RequestParam("file") MultipartFile file) {
		//로그인한사람 프로젝트 내 정보
		PrjVO vo = getParticiInfo(((UserVO) session.getAttribute("user")).getMembUniNo(), prjNo);
		String particiMembUniNo = vo.getParticiMembUniNo();
		
		// 프로젝트참여멤버테이블 업데이트
		//prjService.updateImage();
		//첨부파일등록
		FileVO fileVO = new FileVO();
		fileVO.setParticiMembUniNo(particiMembUniNo);
		//fileService.insertFile(file, fileVO);
			
		return "true";
	}
}
