package com.modu.app.prj.post.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.post.service.MembDTO;
import com.modu.app.prj.post.service.PostDTO;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;

@RestController
public class PostRestController {

	@Autowired
	PostService postService;
	
	@Autowired
	FileService fileService;

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
	
	// 등록
//	@PostMapping("postInsert")
//	public PostDTO postInsert(@RequestBody PostDTO postDTO, HttpSession session) {
//		
//		String particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
//		postDTO.setParticiMembUniNo(particiMembUniNo);
//		
//		PostVO postVO = new PostVO();
//		postVO.setParticiMembUniNo(particiMembUniNo);
//		postVO.setBrdUniNo(postDTO.getBrdUniNo());
//		postVO.setTtl(postDTO.getTtl());
//		postVO.setCm(postDTO.getCm());
//		postVO.setPostTagArm(postDTO.getPostTagArm());
//		
//		//게시글등록
//		postService.insertPost(postVO);
//		
//		//첨부파일등록
//		MultipartFile[] file = postDTO.getAttFiles();
//		System.out.println(file);
//		fileService.insertFileWihtpost(file, postVO);
//			
//		return postDTO;
//	}
	
	//멤버호출용리스트
//	@GetMapping("brdMembs/{bNum}")
//	@ResponseBody
//	public List<MembDTO> chatCallMemb(@PathVariable("bNum") String brdUniNo, HttpSession session){
//		
//		//게시판정보조회
//		PostVO postvo = postService.selectOneBoard(brdUniNo);
//				
//		char isPub = postvo.getPubcYn();
//		if (isPub == 'Y') {
//			String prjUniNo = (String) session.getAttribute("prjUniNo");
//			return postService.selectCallMembPub(prjUniNo);
//		} else if (isPub == 'N') {
//			return postService.selectCallMembNonPub(brdUniNo);
//		}
//		return null;
//	}
	
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
