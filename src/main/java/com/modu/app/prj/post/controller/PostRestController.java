package com.modu.app.prj.post.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;

@RestController
public class PostRestController {
	
	@Autowired
	PostService postService;
	
	//전체조회
	@GetMapping("posts/{brdUniNo}")
	public List<PostVO> postList(@PathVariable String brdUniNo){
		return postService.getAllPostList(brdUniNo);
	}
	
	//단건조회
	@GetMapping("post/{pNum}")
	@CrossOrigin
	public PostVO postOne(@PathVariable ("pNum") String postUniNo) {
		PostVO postVO = postService.getOnePost(postUniNo);
		return postVO;
	}
	
//	//등록
//	@PostMapping("postInsert")
//	public PostVO postInsert(@RequestBody PostVO postVO) {
//		postService.insertPost(postVO);
//		return postVO;
//	}
	/*
	 * //수정
	 * 
	 * @PostMapping("postUpdate") public PostVO postUpdate(@RequestBody PostVO
	 * postVO) { postService.updatePost(postVO); return postVO; }
	 * 
	 * //삭제
	 * 
	 * @GetMapping("postDelete") public String postDelete(String postUniNo) {
	 * postService.deletePost(postUniNo); return postUniNo; }
	 * 
	 * //공지리스트
	 * 
	 * @GetMapping("postsNoti") public List<PostVO> getNotiList() { return
	 * postService.selectAllNotiPost(); }
	 */
	
	

}
