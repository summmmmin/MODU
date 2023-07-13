package com.modu.app.prj.prj.service;

import java.util.List;

public interface PrjService {
	// 프로젝트 생성
	public int insertPrj(PrjVO prjVO);
	
	// 프로젝트참여회원 추가(생성자)
	public int insertPrjPar(PrjVO prjVO);
	
	// 프로젝트 리스트 조회
	public List<PrjVO> getPrjList(String membUniNo);
}
