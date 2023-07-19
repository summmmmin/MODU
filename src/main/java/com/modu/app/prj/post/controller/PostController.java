package com.modu.app.prj.post.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.post.service.MembDTO;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;

@Controller
public class PostController {
	// 230707 김자영 post

	@Autowired
	PostService postService;

	// 전체조회페이지이동
	@GetMapping("postList")
	public String postList(Model model, String brdUniNo) {
		model.addAttribute("brdUniNo", brdUniNo);
		model.addAttribute("postList", postService.getAllPostList(brdUniNo));
		return "post/postList";
	}

	// 단건조회
	@GetMapping("postSelect")
	public String onePost(Model model, String postUniNo) {
		model.addAttribute("post", postService.getOnePost(postUniNo));
		return "post/postInsert";
	}

	// 등록페이지
	@GetMapping("postInsert")
	public String postInsertForm(Model model, String brdUniNo, HttpSession session) {
		
		model.addAttribute("post", postService.selectOneBoard(brdUniNo));
		
		//멤버호출list
	    String prjUniNo = (String) session.getAttribute("prjUniNo");
	    char isPub = postService.selectOneBoard(brdUniNo).getPubcYn();
	    if(isPub == 'Y') {
	    	model.addAttribute("membList", postService.selectCallMembPub(prjUniNo));
	    }else if(isPub == 'N') {
	    	model.addAttribute("membList", postService.selectCallMembNonPub(brdUniNo));
	    }
		return "post/postInsert";
	}

	// 등록처리
	@PostMapping("postInsert")
	public String postInsert(PostVO postVO, HttpSession session) {
		String particiMembUniNo = (String)session.getAttribute("particiMembUniNo");
		postVO.setParticiMembUniNo(particiMembUniNo);
		postService.insertPost(postVO);
		return "redirect:/postList?brdUniNo=" + postVO.getBrdUniNo();
	}

	// 수정페이지
	@GetMapping("postUpdate")
	public String postUpdateForm(Model model, String postUniNo) {
		 PostVO post = postService.getOnePost(postUniNo);
		 model.addAttribute("post", post);
		return "post/postUpdate";
	}

	// 수정처리
	@PostMapping("postUpdate")
	public String postUpdate(PostVO postVO) {
		postService.updatePost(postVO);
		System.out.println(postVO);
		return "redirect:/postList?brdUniNo=" + postVO.getBrdUniNo();
	}



}
