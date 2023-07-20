package com.modu.app.prj.post.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	// 전체조회
	@GetMapping("posts/{bNum}")
	public List<PostVO> postList(@PathVariable("bNum") String brdUniNo) {
		return postService.getAllPostList(brdUniNo);
	}

	// 단건조회
	@GetMapping("post/{pNum}")
	public PostVO postOne(@PathVariable("pNum") String postUniNo) {
		return postService.getOnePost(postUniNo);
	}

	//삭제
	@GetMapping("postDelete/{pNum}")
	public String postDelete(@PathVariable("pNum") String postUniNo) {
		postService.deletePost(postUniNo);
		return postUniNo;
	}
//	@DeleteMapping("post/{pNum}")
//	public String postDelete(@PathVariable("pNum") String postUniNo) {
//		postService.deletePost(postUniNo);
//		return postUniNo;
//	}
	
	//공지등록ON/OFF
	@PostMapping("setPostNoti")
	public PostVO setPostNoti (@RequestBody PostVO postVO) {
		postService.notiOnOff(postVO);
		return postVO;
	}
	
	//상단공지리스트
	@GetMapping("notiPost/{bNum}")
	public List<PostVO> notiPostList(@PathVariable("bNum") String brdUniNo){
		return postService.selectAllNotiPost(brdUniNo);
	}
	
	//댓글알림 on/off
	@PostMapping("setPostReply")
	public PostVO setPostReply(@RequestBody PostVO postVO) {
		postService.replyOnOff(postVO);
		return postVO;
	}

}
