package com.modu.app.prj.post.service;

import java.util.List;

public interface ReplyService {

	//전체조회
	public List<ReplyVO> getAllReplyList(String postUniqueNumber);
	
	//단건조회
	public ReplyVO getOneReply(String replyUniqueNumber);
	
	//등록
	public int insertReply(ReplyVO replyeVO);
	
	//수정
	public int updateReply(ReplyVO replyVO);
	
	//삭제
	public int deleteReply(String replyUniqueNumber);

}
