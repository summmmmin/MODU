package com.modu.app.prj.vote.service;

import java.util.List;


public interface VoteService {
	//투표 목록
	List<VoteVO> getVoteList();
	
	//투표 단건 조회
	VoteVO getVote();
	
	// 투표 생성
	int insertVote(VoteVO voteVo);
	
	// 투표 하기
	int doVote(VoteVO voteVo);
	
	//투표 기간 연장
	int updateVote(VoteVO voteVo);
	
	//투표 카운트
	int selectCount(VoteVO voteVo);
	
	//투표 항목 리스트
	List<VoteVO> getVoteItemList();
	
	//투표 참여자
	int voteMember(VoteVO voteVo);
	
}
