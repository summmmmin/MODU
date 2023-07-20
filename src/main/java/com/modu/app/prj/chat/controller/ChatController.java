package com.modu.app.prj.chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import com.modu.app.prj.chat.service.ChatService;
import com.modu.app.prj.chat.service.ChatVO;
import com.modu.app.prj.chat.service.ChatrDTO;
import com.modu.app.prj.chat.service.ChatrVO;
import com.modu.app.prj.post.service.PostService;


@Controller
public class ChatController {
	
	
	@Autowired
	private WebSocketHandler webSocketHandler;
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	PostService postService;
	
	@GetMapping("/chatPage") 
	public String chatPage() {
		return "chat/chat";
	}

	@MessageMapping("/chat") 
	@SendTo("/sub/chat")
	public ChatVO greeting(ChatVO chatVO) throws Exception {
		Thread.sleep(1000); // simulated delay
		//return new ChatVO("Hello, " + HtmlUtils.htmlEscape(message.getCntn()) + "!");
		
		System.out.println(chatVO);
		return new ChatVO(HtmlUtils.htmlEscape(chatVO.getCntn()));
		
	}
	
	//채팅방만들기? form없이 만들것
	@GetMapping("makeChatr") 
	public String makeChatrForm(Model model, HttpSession session) {
		String prjUniNo = (String) session.getAttribute("prjUniNo");
		model.addAttribute("membList", postService.selectCallMembPub(prjUniNo));
		return "chat/makeChatr";
	}
	
	@PostMapping("makeChatr")
	public ChatrDTO makeChatr(ChatrDTO chatrDTO, HttpSession session) {
		
		String prjUniNo = (String) session.getAttribute("prjUniNo");
		String particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
		
		//채팅방만든사람도 참여멤버리스트에 넣음
		chatrDTO.getParticiMembUniNos().add(particiMembUniNo);
		
		//채팅방 INSERT 시 필요한 VO
		ChatrVO chatrVO = new ChatrVO();
		chatrVO.setPrjUniNo(prjUniNo);
		System.out.println(chatrDTO);
		chatService.makeChatr(chatrVO);
		
		return chatrDTO;
	}

}
