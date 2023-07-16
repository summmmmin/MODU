package com.modu.app.prj.prj.mapper;

import java.util.List;

import com.modu.app.prj.prj.service.PrjVO;

public interface PrjMapper {
	// 프로젝트 생성
	public int insertPrj(PrjVO prjVO);

	// 프로젝트 리스트 조회
	public List<PrjVO> selectPrjList(String membUniNo);

	// 프로젝트 참여 회원 리스트
	public List<PrjVO> selectPrjParti(PrjVO prjVO);
	
	// 프로젝트번호 세션
	public PrjVO prjSession(PrjVO vo);
	
	// 등급공통코드
	public List<PrjVO> grdCmmn();

}
