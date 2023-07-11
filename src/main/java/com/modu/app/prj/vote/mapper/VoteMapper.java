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
}
