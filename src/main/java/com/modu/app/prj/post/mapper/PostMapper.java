package com.modu.app.prj.post.mapper;

import java.util.List;

import com.modu.app.prj.post.service.PostVO;

public interface PostMapper {
	
	//전체개시글조회
	public List<PostVO> selectAllPost();
	
	//단건조회
	public PostVO selectOnePost(String postUniqueNumber);
	
	//등록
	public int insertPost(PostVO postVO);
	
	//수정
	public int updatePost(PostVO postVO);
	
	//삭제
	public int deletePost(String postUniqueNumber);
}
