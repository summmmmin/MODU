package com.modu.app.prj.post.service;

import java.util.List;


public interface PostService {
	
	//전체조회
	public List<PostVO> getAllPostList();
	
	//단건조회
	public PostVO getOnePost(String postUniqueNumber);
	
	//등록
	public int insertPost(PostVO postVO);
	
	//수정
	public int updatePost(PostVO postVO);
	
	//삭제
	public int deletePost(String postUniqueNumber);
}
