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
}
