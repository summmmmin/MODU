package com.modu.app.prj.post.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.post.mapper.PostMapper;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostMapper postMapper;
	
	@Override
	public List<PostVO> getAllPostList() {
		return postMapper.selectAllPost();
	}

	@Override
	public PostVO getOnePost(String postUniqueNumber) {
		return postMapper.selectOnePost(postUniqueNumber);
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
	public int deletePost(String postUniqueNumber) {
		return postMapper.deletePost(postUniqueNumber);
	}

}
