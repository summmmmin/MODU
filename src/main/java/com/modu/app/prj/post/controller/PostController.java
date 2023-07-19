package com.modu.app.prj.post.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.modu.app.prj.file.mapper.FileMapper;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.post.service.PostVO;

@Controller
public class PostController {
	// 230707 김자영 post

	@Autowired
	PostService postService;

	@Autowired
	FileService fileService;

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

		// 멤버호출list
		String prjUniNo = (String) session.getAttribute("prjUniNo");
		char isPub = postService.selectOneBoard(brdUniNo).getPubcYn();
		if (isPub == 'Y') {
			model.addAttribute("membList", postService.selectCallMembPub(prjUniNo));
		} else if (isPub == 'N') {
			model.addAttribute("membList", postService.selectCallMembNonPub(brdUniNo));
		}
		return "post/postInsert";
	}

	// 등록처리
	@PostMapping("postInsert")
	public String postInsert(PostVO postVO, HttpSession session,
			@RequestParam(name = "file", required = false) MultipartFile file) {
		String particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
		postVO.setParticiMembUniNo(particiMembUniNo);
		
		//게시글등록
		postService.insertPost(postVO);

		// 첨부파일 있을 때
		if (file != null && !file.isEmpty()) {
			long fileSize = file.getSize();
			String fileExtension = getFileExtension(file.getOriginalFilename());

			FileVO fileVO = new FileVO();
			fileVO.setParticiMembUniNo(particiMembUniNo);
			fileVO.setAttNm(file.getOriginalFilename());
			String uuid = UUID.randomUUID().toString();
			String uploadFileName = uuid + "_" + file.getOriginalFilename();
			String saveName = uploadFileName;
			fileVO.setServerAttNm(saveName);
			fileVO.setFSize(fileSize);
			fileVO.setExt(fileExtension);

			fileService.insertFile(file, fileSize, fileExtension, particiMembUniNo);

		} 
			
		
		return "redirect:/postList?brdUniNo=" + postVO.getBrdUniNo();
	}

	// 파일 확장자 추출 메소드 (동일한 코드를 FileController와 PostController에서 사용)
	private String getFileExtension(String filename) {
		if (filename == null)
			return null;
		int extensionIndex = filename.lastIndexOf(".");
		return (extensionIndex == -1) ? null : filename.substring(extensionIndex + 1).toLowerCase();
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
