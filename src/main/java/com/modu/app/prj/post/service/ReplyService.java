package com.modu.app.prj.post.service;

import java.util.List;

public interface ReplyService {

	//댓글전체조회
	public List<ReplyVO> getAllReplyList(String postUniNo);
	
	//댓글단건조회
	public ReplyVO getOneReply(String replyUniNo);
	
	//댓글등록
	public int insertReply(ReplyVO replyeVO);
	
	//댓글수정
	public int updateReply(ReplyVO replyVO);
	
	//댓글삭제
	public int deleteReply(String replyUniNo);

}
