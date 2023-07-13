package com.modu.app.prj.post.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.post.mapper.PostMapper;
import com.modu.app.prj.post.mapper.ReplyMapper;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostMapper postMapper;
	@Autowired
	ReplyMapper replyMapper;
	
	//게시글전체조회
	@Override
	public List<PostVO> getAllPostList(String brdUniNo) {
		return postMapper.selectAllPost(brdUniNo);
	}
	
	//게시글단건조회
	@Override
	public PostVO getOnePost(String postUniNo) {
		return postMapper.selectOnePost(postUniNo);
	}
	//게시글등록, 수정폼용 게시판조회
	@Override
	public PostVO selectOneBoard(String postUniNo) {
		PostVO postVO = postMapper.selectOneBoard(postUniNo);
		return postVO;
	}
	//게시글등록
	@Override
	public int insertPost(PostVO postVO) {
		return postMapper.insertPost(postVO);
	}
	
	//게시글수정
	@Override
	public int updatePost(PostVO postVO) {
		return postMapper.updatePost(postVO);
	}
	
	//게시글삭제
	@Override
	public int deletePost(String postUniNo) {
		return postMapper.deletePost(postUniNo);
	}
	
	//댓글알림on/off
	@Override
	public int replyOnOff(String postUniNo) {
		return postMapper.replyOnOff(postUniNo);
	}
	
	//공지등록on/off
	@Override
	public int notiOnOff(String postUniNo) {
		return postMapper.notiOnOff(postUniNo);
	}
	
	//등록된공지리스트
	@Override
	public List<PostVO> selectAllNotiPost() {
		return postMapper.selectAllNotiPost();
	}


}
