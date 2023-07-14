package com.modu.app.prj.prj.service;

import java.util.List;

public interface PrjService {
	// 프로젝트 생성
	public int insertPrj(PrjVO prjVO);
	
	// 프로젝트 리스트 조회
	public List<PrjVO> getPrjList(String membUniNo);
	
	// 프로젝트 참여 회원 리스트
	public List<PrjVO> getPrjPartiList(PrjVO prjVO);

	public PrjVO prjSession(PrjVO vo);
}
