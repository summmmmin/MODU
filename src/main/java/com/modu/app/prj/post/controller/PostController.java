package com.modu.app.prj.post.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;

@Controller
public class PostController {
	// 230707 김자영 post

	@Autowired
	PostService postService;
	

	//전체조회ㅇ
	@GetMapping("postList")
	public String postList(Model model, String brdUniNo) {
		model.addAttribute("postList", postService.getAllPostList(brdUniNo));
		return "post/postList";
	}
	
	//단건조회
	@GetMapping("postSelect")
	public String onePost(Model model, String postUniqueNumber) {
		model.addAttribute("post", postService.getOnePost(postUniqueNumber));
		return "post/postInsert";
	}
	
	/*
	//등록페이지
	@GetMapping("postInsert")
	public void postInsertForm(Model model) {
		model.addAttribute("post", new PostVO());
	}
	
	//등록처리
	@PostMapping("postInsert")
	public String postInsert(PostVO postVO) {
		postService.insertPost(postVO);
		return "redirect:postList";
	}
	
	//수정페이지
	@GetMapping("postUpdate")
	public void postUpdateForm(Model model, String postUniqueNumber) {
		model.addAttribute("post", postService.getOnePost(postUniqueNumber));
	}
	
	//수정처리
	@PostMapping("postUpdate")
	public String postUpdate(PostVO postVO) {
		postService.updatePost(postVO);
		return ""; //어디로가?
	}
	
	//삭제
	@GetMapping("postDelete")
	public String postDelete(String postUniqueNumber) {
		postService.deletePost(postUniqueNumber);
		return postUniqueNumber;
	}
	*/

}
