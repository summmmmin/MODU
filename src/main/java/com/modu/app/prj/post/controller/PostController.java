package com.modu.app.prj.post.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.bm.service.BmService;
import com.modu.app.prj.bm.service.BmVO;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;
// 230707 김자영 post

@Controller
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	FileService fileService; //첨부파일용
	
	@Autowired
	BmService bmService;

	// 전체조회페이지이동
	@GetMapping("postList")
	public String postList(Model model, String brdUniNo, BmVO vo, HttpSession session) {
		vo.setParticiMembUniNo((String) session.getAttribute("particiMembUniNo")); 
		model.addAttribute("brdUniNo", brdUniNo);
		model.addAttribute("postList", postService.getAllPostList(brdUniNo));
		model.addAttribute("bmList",bmService.BmList(vo));
		System.out.println("qqqqqq"+postService.getAllPostList(brdUniNo));
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
		
		//게시판정보조회
		PostVO postvo = postService.selectOneBoard(brdUniNo);
		model.addAttribute("post", postvo);

		// 게시판공개여부에 따라 멤버조회
		char isPub = postvo.getPubcYn();
		if (isPub == 'Y') {
			String prjUniNo = (String) session.getAttribute("prjUniNo");
			model.addAttribute("membList", postService.selectCallMembPub(prjUniNo));
		} else if (isPub == 'N') {
			model.addAttribute("membList", postService.selectCallMembNonPub(brdUniNo));
		}

		return "post/postInsert";
	}

	// 등록처리 
	@PostMapping("postInsert")
	public String postInsert(PostVO postVO, HttpSession session, MultipartFile[] file) {
		
		String particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
		postVO.setParticiMembUniNo(particiMembUniNo);
		
		//게시글등록
		postService.insertPost(postVO);
		//첨부파일등록
		fileService.insertFileWihtpost(file, postVO);
			
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
		return "redirect:/postList?brdUniNo=" + postVO.getBrdUniNo();
	}

}
