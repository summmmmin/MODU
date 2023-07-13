package com.modu.app.prj.vote.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.vote.mapper.VoteMapper;
import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	VoteMapper voteMapper;
	
	@Override
	public List<VoteVO> getVoteList() {
		
		return null;
	}

	@Override
	public VoteVO getVote() {
		
		return null;
	}

	@Override
	public int updateVote(VoteVO vo) {
		
		return 0;
	}

	@Override
	public int selectCount(VoteVO vo) {
		
		return 0;
	}

	@Override
	public List<VoteVO> getVoteItemList() {
		
		return null;
	}

	@Override
	public int voteMember(VoteVO vo) {
		
		return 0;
	}

	@Override
	public int insertVote(VoteVO voteVo) {
		return 0;
	}

	@Override
	public int doVote(VoteVO voteVo) {
		return 0;
	}

	@Override
	public VoteVO voteChatr(String voteNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoteVO voteBrdNm(String voteNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
