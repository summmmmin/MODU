package com.modu.app.prj.arm.controller;



import javax.servlet.http.HttpSession;

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
	
	
	//이거웹소켓?
	@MessageMapping("/reply/{memno}") 
	//@SendTo("/chat/msg/{chatrNo}")
	public void arm(ArmVO vo,@DestinationVariable String memno) throws Exception {
	    // 클라이언트로부터 받은 메시지를 다시 /sub/chat 주제로 발행
		//String particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
		messagingTemplate.convertAndSend("/subArm/reply/"+memno,vo);
		
	}
	
}
