package com.modu.app.prj.post.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.post.mapper.ReplyMapper;
import com.modu.app.prj.post.service.ReplyService;
import com.modu.app.prj.post.service.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyMapper replyMapper;
	
	//댓글전체조회
	@Override
	public List<ReplyVO> getAllReplyList(String postUniNo) {
		return replyMapper.selectAllReply(postUniNo);
	}
	
	//댓글단건조회
	@Override
	public ReplyVO getOneReply(String replyUniNo) {
		return replyMapper.selectOneReply(replyUniNo);
	}

	//댓글등록
	@Override
	public int insertReply(ReplyVO replyeVO) {
		return replyMapper.insertReply(replyeVO);
	}
	
	//댓글수정
	@Override
	public int updateReply(ReplyVO replyVO) {
		return replyMapper.updateReply(replyVO);
	}

	//댓글삭제
	@Override
	public int deleteReply(String replyUniNo) {
		return replyMapper.deleteReply(replyUniNo);
	}

}
