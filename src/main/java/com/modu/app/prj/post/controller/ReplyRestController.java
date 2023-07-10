package com.modu.app.prj.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.modu.app.prj.post.service.ReplyService;
import com.modu.app.prj.post.service.ReplyVO;

@RestController
public class ReplyRestController {
	// 230708 김자영 reply
	
	@Autowired
	ReplyService replyService;
	
	//전체조회
	@CrossOrigin
	@GetMapping("replys/{pNum}")
	public List<ReplyVO> replyList(@PathVariable("pNum") String postUniNo) {
		return replyService.getAllReplyList(postUniNo);
	}
	
	//단건조회
	@GetMapping("reply/{rNum}")
	@CrossOrigin
	public ReplyVO replyOne(@PathVariable("rNum") String replyUniNo) {
		return replyService.getOneReply(replyUniNo);
	}
	
	//등록
	@PostMapping("replyInsert")
	public ReplyVO replyInsert(@RequestBody ReplyVO replyVO) {
		replyService.insertReply(replyVO);
		return replyVO;
	}
	
	//수정
	@PostMapping("replyUpdate")
	public ReplyVO replyUpdate(@RequestBody ReplyVO replyVO) {
		replyService.updateReply(replyVO);
		return replyVO;
	}
	
	//삭제
	@GetMapping("replyDelete")
	public String replyDelete(String replyUniNo) {
		replyService.deleteReply(replyUniNo);
		return replyUniNo;
	}
	

	
}
