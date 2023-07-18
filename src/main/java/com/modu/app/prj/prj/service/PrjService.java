package com.modu.app.prj.prj.service;

import java.util.List;

public interface PrjService {
	// 프로젝트 생성
	public int insertPrj(PrjVO prjVO);
	
	// 프로젝트 리스트 조회
	public List<PrjVO> getPrjList(String membUniNo);
	
	// 프로젝트 참여 회원 리스트
	public List<PrjVO> getPrjPartiList(PrjVO prjVO);
	// 프로젝트 세션
	public PrjVO prjSession(PrjVO vo);
	
	// 등급 공통코드
	public List<PrjVO> grdCmmn();
	
	// 프로젝트 이름 변경
	public int setPrjNm(PrjVO vo);
	
	// 프로젝트 삭제
	public int delPrj(String prjNo);
	
	// 프로젝트 참여 회원 등급변경
	public int setGrade(PrjVO vo);
	
	// 프로젝트 회원 탈퇴
	public int kickPrjParti(PrjVO vo);
	
	// 프로젝트 회원 정보
	public PrjVO getMemInfo(PrjVO vo);
}
