package com.modu.app.prj.vote.mapper;

import java.util.List;

import com.modu.app.prj.vote.service.VoteDetaVO;
import com.modu.app.prj.vote.service.VoteVO;

// 2023/07/21 김동건
public interface VoteMapper {
	
	
	
	 
	//투표 생성하기 
	public int voteInsert(VoteVO vo);
	
	//투표 리스트
	public List<VoteVO>voteList(VoteVO vo);
	
	//투표 단건 조회(투표 하기폼)
	public VoteVO oneVote(VoteVO vo);
	
	//투표 항목 나열
	public List<VoteDetaVO> voteItem(String vno);
	
	//투표하기 
	public int voteDo(VoteDetaVO vo);
	
	//투표 기간 수정
	public int voteDate(VoteVO voteVo);	
	
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
	
	
	
}
