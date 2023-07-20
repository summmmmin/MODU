package com.modu.app.prj.vote.mapper;

import java.util.List;

import com.modu.app.prj.vote.service.VoteVO;


public interface VoteMapper {
	
	//투표 생성 - 1-1. 게시글에 첨가
	public int postVote(VoteVO voteVo);
	
	//투표 생성 - 1-2. 채팅에 첨가
	public int chatVote(VoteVO voteVo);
	
	//투표 생성 - 3.투표상세 테이블
	public int insertVoteItem(VoteVO voteVO);
	
	//투표 기간 수정
	public int voteDate(VoteVO voteVo);
	
	//투표 항목 나열
	public List<VoteVO> voteItem();
	
	//투표하기 - 투표수 증가
	public int updateCnt(VoteVO voteVo);
	
	//투표하기 - 투표 참가자
	public int whoVote(VoteVO voteVo);
	
	//투표결과
	public List<VoteVO> voteResult();
	
	//투표번호 채팅방 이름찾기
	public VoteVO voteChatr(String voteNo);
	
	//투표번호 게시판 이름 찾기
	public VoteVO voteBrdNm(String voteNo);
	
	//채팅방 제목
	public List<VoteVO> chatrNm(String no);
	
	//게시판 제목
	public List<VoteVO> brdNm(String no);
	
	//투표 리스트
	public List<VoteVO>voteList(VoteVO vo);
	
	
}
