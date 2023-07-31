package com.modu.app.prj.prj.service;

import java.util.List;
import java.util.Map;

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
	
	// 프로젝트 참여 회원수
	public int getPrjMemCnt(String prjUniNo);
	
	// 프로젝트 정보
	public PrjVO getPrjInfo(String prjUniNo);
	
	// 프로젝트 구독 해지/재시작
	public int setPrjSubsp(PrjVO vo);
	
	// 프로젝트 게시판 수
	public int getBrdCnt(String prjUniNo);
	
	// 프로젝트 채팅방 수
	public int getChatrCnt(String prjUniNo);
	
	// 프로젝트의 회원 별 게시글,답글,채팅 수(기간내)
	public List<PrjVO> getPrjCnt(PrjVO vo);
	
	// 프로젝트 등급별 인원
	public Map<String, Object> getGrdCnt(String prjUniNo);
	
	// 초대시 insert
	public String insertInvite(PrjVO vo);
	
	// 초대테이블 조회
	public PrjVO selectInvite(String tk);
	
	// 프로젝트 참여 테이블 insert
	public int insertPartiMemb(PrjVO vo);
}
