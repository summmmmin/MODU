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
	
	
	//투표 생성 - 1-1. 게시글에 첨가
	@Override
	public int postVote(VoteVO voteVo) {
		return voteMapper.postVote(voteVo);
	}

	//투표 생성 - 1-2. 채팅에 첨가
	@Override
	public int chatVote(VoteVO voteVo) {
		return voteMapper.chatVote(voteVo);
	}
	
	//투표 생성 - 3.투표상세 테이블
	@Override
	public int insertVoteItem(VoteVO voteVO) {
		return voteMapper.insertVoteItem(voteVO);
	}

	//투표 기간 수정
	@Override
	public int voteDate(VoteVO voteVo) {
		return voteMapper.voteDate(voteVo);
	}
	
	//투표 항목 나열
	@Override
	public List<VoteVO> voteItem() {
		return voteMapper.voteItem();
	}
	
	//투표하기 - 투표수 증가
	@Override
	public int updateCnt(VoteVO voteVo) {
		return voteMapper.updateCnt(voteVo);
	}

	//투표하기 - 투표 참가자
	@Override
	public int whoVote(VoteVO voteVo) {
		return voteMapper.whoVote(voteVo);
	}

	//투표결과
	@Override
	public List<VoteVO> voteResult() {
		return voteMapper.voteResult();
	}

	//투표번호 채팅방 이름찾기
	@Override
	public VoteVO voteChatr(String voteNo) {
		return voteMapper.voteChatr(voteNo);
	}

	//투표번호 게시판 이름 찾기
	@Override
	public VoteVO voteBrdNm(String voteNo) {
		return voteMapper.voteBrdNm(voteNo);
	}

	@Override
	public List<VoteVO> chatrNm(String no) {
		return voteMapper.chatrNm(no);
	}

	@Override
	public List<VoteVO> brdNm(String no) {
		return voteMapper.brdNm(no);
	}

	@Override
	public List<VoteVO> voteList(VoteVO vo) {
		return voteMapper.voteList(vo);
	}
	

}
