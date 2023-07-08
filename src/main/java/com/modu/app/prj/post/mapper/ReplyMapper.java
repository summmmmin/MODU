package com.modu.app.prj.post.mapper;

import java.util.List;

import com.modu.app.prj.post.service.ReplyVO;

public interface ReplyMapper {
	
	//게시글댓글전체조회
	public List<ReplyVO> selectAllReply(String postUniqueNumber);
	
	//단건조회
	public ReplyVO selectOneReply(String replyeUniqueNumber);
	
	//등록
	public int insertReply(ReplyVO replyeVO);
	
	//수정
	public int updateReply(ReplyVO replyeVO);
	
	//삭제
	public int deleteReply(String replyUniqueNumber);
}
