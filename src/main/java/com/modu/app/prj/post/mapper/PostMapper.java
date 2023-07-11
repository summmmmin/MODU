package com.modu.app.prj.post.mapper;

import java.util.List;

import com.modu.app.prj.post.service.PostVO;

public interface PostMapper {
	
	//전체개시글조회
	public List<PostVO> selectAllPost(String brdUniNo);
	
	//단건조회
	public PostVO selectOnePost(String postUniNo);
	
	//등록
	public int insertPost(PostVO postVO);
	
	//수정
	public int updatePost(PostVO postVO);
	
	//삭제
	public int deletePost(String postUniNo);
	
	//댓글알림on/off
	public int replyOnOff(String postUniNo);
	
	//공지등록on/off
	public int notiOnOff(String postUniNo);
		
	//공지리스트
	public List<PostVO> selectAllNotiPost();
	
	//멤버호출용
	

}
