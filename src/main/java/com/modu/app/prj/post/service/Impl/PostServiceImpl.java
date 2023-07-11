package com.modu.app.prj.post.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.post.mapper.PostMapper;
import com.modu.app.prj.post.mapper.ReplyMapper;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;
import com.modu.app.prj.post.service.ReplyVO;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostMapper postMapper;
	@Autowired
	ReplyMapper replyMapper;
	
	@Override
	public List<PostVO> getAllPostList(String brdUniNo) {
		return postMapper.selectAllPost(brdUniNo);
	}

	@Override
	public PostVO getOnePost(String postUniNo) {
		return postMapper.selectOnePost(postUniNo);
	}

	@Override
	public int insertPost(PostVO postVO) {
		return postMapper.insertPost(postVO);
	}

	@Override
	public int updatePost(PostVO postVO) {
		return postMapper.updatePost(postVO);
	}

	@Override
	public int deletePost(String postUniNo) {
		return postMapper.deletePost(postUniNo);
	}

	@Override
	public int replyOnOff(String postUniNo) {
		return postMapper.replyOnOff(postUniNo);
	}

	@Override
	public int notiOnOff(String postUniNo) {
		return postMapper.notiOnOff(postUniNo);
	}

	@Override
	public List<PostVO> selectAllNotiPost() {
		return postMapper.selectAllNotiPost();
	}

}
