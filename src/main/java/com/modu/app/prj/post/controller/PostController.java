package com.modu.app.prj.post.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;
import com.modu.app.prj.user.service.UserVO;

@Controller
public class PostController {
	// 230707 김자영 post

	@Autowired
	PostService postService;

	// 전체조회페이지이동
	@GetMapping("postList")
	public String postList(Model model, String brdUniNo, HttpSession session) {
//		//test용 이하 세션 삭제예정
//		session.setAttribute("prjUniNo", "punt1");
//		session.setAttribute("particiMembUniNo", "ppmt1");
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
	public String postInsertForm(Model model, PostVO vo) {
		vo = postService.selectOneBoard(vo);
		model.addAttribute("post", vo);
		return "post/postInsert";
	}

	// 등록처리
	@PostMapping("postInsert")
	public String postInsert(Model model, PostVO postVO, HttpSession session) {
		
		String nm = (String)session.getAttribute("particiMembUniNo");
		postVO.setParticiMembUniNo(nm);
		postService.insertPost(postVO);
		System.out.println(postVO);
		//List<PostVO> postList = postService.getAllPostList(postVO.getBrdUniNo());
		//model.addAttribute("postList", postList);
		return "redirect:postList";
	}

	// 수정페이지
	@GetMapping("postUpdate")
	public String postUpdateForm(Model model, String postUniNo) {
		model.addAttribute("post", postService.getOnePost(postUniNo));
		return "post/postUpdate";
	}

	// 수정처리
	@PostMapping("postUpdate")
	public String postUpdate(PostVO postVO) {
		postService.updatePost(postVO);
		return ""; // 어디로가?
	}

	// 삭제
	@GetMapping("postDelete")
	public String postDelete(String postUniqueNumber) {
		postService.deletePost(postUniqueNumber);
		return postUniqueNumber;
	}

}
