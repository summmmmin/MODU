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

	@Override
	public List<ReplyVO> getAllReplyList(String postUniNo) {
		return replyMapper.selectAllReply(postUniNo);
	}

	@Override
	public ReplyVO getOneReply(String replyUniNo) {
		return replyMapper.selectOneReply(replyUniNo);
	}

	@Override
	public int insertReply(ReplyVO replyeVO) {
		return replyMapper.insertReply(replyeVO);
	}

	@Override
	public int updateReply(ReplyVO replyVO) {
		return replyMapper.updateReply(replyVO);
	}

	@Override
	public int deleteReply(String replyUniNo) {
		return replyMapper.deleteReply(replyUniNo);
	}

}
