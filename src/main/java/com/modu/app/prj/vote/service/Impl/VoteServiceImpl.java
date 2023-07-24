package com.modu.app.prj.vote.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.vote.mapper.VoteMapper;
import com.modu.app.prj.vote.service.VoteDetaVO;
import com.modu.app.prj.vote.service.VoteService;
import com.modu.app.prj.vote.service.VoteVO;

//2023/07/21 김동건
@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	VoteMapper voteMapper;
	
	//투표 생성하기
	public int voteInsert(VoteVO vo) {
		return voteMapper.voteInsert(vo);
	}

	//투표 기간 수정
	@Override
	public int voteDate(VoteVO voteVo) {
		return voteMapper.voteDate(voteVo);
	}
	
	//투표 항목 나열
	@Override
	public List<VoteDetaVO> voteItem(String vno) {
		return voteMapper.voteItem(vno);
	}
	
	//투표하기 
	@Override
	public int voteDo(VoteDetaVO vo) {
			return	voteMapper.voteDo(vo);
		
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

	//투표 리스트
	@Override
	public List<VoteVO> voteList(VoteVO vo) {
		return voteMapper.voteList(vo);
	}
	
	
	//투표 단건 조회
	@Override
	public VoteVO voteOne(VoteVO vo) {
		return voteMapper.oneVote(vo);
	}
	
	//재투표 방지
	@Override
	public VoteDetaVO whoVote(VoteDetaVO vo) {
		return voteMapper.whoVote(vo);
	}
	
	
	
	//채티방 제목
	@Override
	public List<VoteVO> chatrNm(String no) {
		return voteMapper.chatrNm(no);
	}

	//게시판 제목
	@Override
	public List<VoteVO> brdNm(String no) {
		return voteMapper.brdNm(no);
	}
	
	//투표 제작자
	public VoteVO voteMaker(String vid) {
		return voteMapper.voteMaker(vid);
	}

	//투표 참여자수
	public VoteDetaVO voteCount(String vno) {
		return voteMapper.voteCount(vno);
	}

	@Override
	public VoteDetaVO allCount() {
		return voteMapper.allCount();
	}


}
