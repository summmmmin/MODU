package com.modu.app.prj.arm.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.modu.app.prj.arm.service.ArmVO;

@Validated
@Controller
public class ArmController {
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	
	//댓글 등록 알림
	@MessageMapping("/reply/{memno}") 
	public void arm(ArmVO vo,@DestinationVariable String memno) throws Exception {
	    // 클라이언트로부터 받은 메시지를 다시 /sub/rply 주제로 발행
		messagingTemplate.convertAndSend("/subArm/reply/"+memno,vo);
	}
	
	//댓글 등록 알림
	@MessageMapping("/brd/{memno}")
	public void brdarm(ArmVO vo,@DestinationVariable String memno) throws Exception {
		messagingTemplate.convertAndSend("/subArm/brd/"+memno,vo);
	}
	
}
