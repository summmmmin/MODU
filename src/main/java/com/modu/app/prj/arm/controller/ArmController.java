package com.modu.app.prj.arm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modu.app.prj.arm.service.ArmVO;
import com.modu.app.prj.board.service.BoardService;
import com.modu.app.prj.board.service.BoardVO;

@Validated
@Controller
public class ArmController {

	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	BoardService boardservice;

	// 댓글 등록 알림
	@MessageMapping("/reply/{memno}")
	public void arm(ArmVO vo, @DestinationVariable String memno) throws Exception {
		messagingTemplate.convertAndSend("/subArm/reply/" + memno, vo);
	}

	// 댓글 등록 알림
	@MessageMapping("/brd/{memno}")
	public void brdarm(ArmVO vo, @DestinationVariable String memno) throws Exception {
		messagingTemplate.convertAndSend("/subArm/brd/" + memno, vo);
	}

	// 채팅 등록 알림
	@MessageMapping("/chat/{memno}")
	public void chatarm(ArmVO vo, @DestinationVariable String memno) throws Exception {
		messagingTemplate.convertAndSend("/subArm/chat/" + memno, vo);
	}
	
	//채팅방 참여회원 리스트
	@GetMapping("ChatParticiMemb")
	@ResponseBody
	public List<BoardVO> chatParticiMemb(BoardVO vo, HttpSession session ){
		String chatr = (String)session.getAttribute("chatrNo");
		vo.setChatrNo(chatr);
		System.out.println(vo);
		return boardservice.chatParticiMemb(vo);
	}
	

	// 할 일 등록 알림
	@MessageMapping("/todo/{memno}")
	public void todoarm(ArmVO vo, @DestinationVariable String memno) throws Exception {
		messagingTemplate.convertAndSend("/subArm/todo/" + memno, vo);
	}
	
	// 비공개 게시판 초대  알림
	@MessageMapping("/partici/{memno}")
	public void particiarm(ArmVO vo, @DestinationVariable String memno) throws Exception {
		messagingTemplate.convertAndSend("/subArm/partici/" + memno, vo);
	}
	
	// 게시글 태그 알림
	@MessageMapping("/tag/{memno}")
	public void tagarm(ArmVO vo, @DestinationVariable String memno) throws Exception {
		messagingTemplate.convertAndSend("/subArm/tag/" + memno, vo);
	}
	
		// 게시글 태그 알림
		@MessageMapping("/noto/{memno}")
		public void notiarm(ArmVO vo, @DestinationVariable String memno) throws Exception {
			messagingTemplate.convertAndSend("/subArm/noti/" + memno, vo);
		}

}
