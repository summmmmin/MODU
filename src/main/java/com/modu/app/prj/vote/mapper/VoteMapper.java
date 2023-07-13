package com.modu.app.prj.vote.mapper;

import java.util.List;

import com.modu.app.prj.vote.service.VoteVO;


public interface VoteMapper {
	//투표 목록 조회
	public List<VoteVO> selectVoteList();
	
	//투표 단건 조회
	public VoteVO selectVote(VoteVO voteVo);
	
	//투표 생성
	public int insertEmpinfo(VoteVO voteVo);
	
	//투표 생성 - 1-1. 게시글에 첨가
	public int postVote(VoteVO voteVo);
	
	//투표 생성 - 1-2. 채팅에 첨가
	public int chatVote(VoteVO voteVo);
	
	//투표 생성 - 3.투표상세 테이블
	
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
}
