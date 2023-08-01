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
	
	// 프로젝트 이름 변경
	public int updatePrjNm(PrjVO vo);
	
	// 프로젝트 삭제
	public int deletePrj(String prjNo);
	
	// 프로젝트 참여 회원 등급변경
	public int updateGrade(PrjVO vo);
	
	// 프로젝트 회원 탈퇴
	public int kickPrjParti(PrjVO vo);
	
	// 프로젝트 회원 정보
	public PrjVO selectMemInfo(PrjVO vo);
	
	// 프로젝트 참여 회원수
	public int selectPrjMemCnt(String prjUniNo);
	
	// 프로젝트 정보
	public PrjVO selectPrjInfo(String prjUniNo);
	
	// 프로젝트 구독 취소
	public int updatePrjSubsp(PrjVO vo);
	
	// 프로젝트 게시판 수
	public int selectBrdCnt(String prjUniNo);
	
	// 프로젝트 채팅방 수
	public int selectChatrCnt(String prjUniNo);
	
	// 프로젝트의 회원 별 게시글,답글,채팅 수(기간내)
	public List<PrjVO> selectPrjCnt(PrjVO vo);
	
	//  프로젝트 등급별 인원
	public List<PrjVO> selectGrdCnt(String prjUniNo);
	
	// 초대테이블 insert
	public void insertInvite(PrjVO vo);
	
	// 초대테이블 조회
	public PrjVO selectInvite(String tk);
	
	// 초대 멤버 프로젝트 참여 테이블로
	public int insertPartiMemb(PrjVO vo);
	
	// 초대테이블 확인 여부 업뎃
	public int updateInvite(String id);
}
