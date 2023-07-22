package com.modu.app.prj.chat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import com.modu.app.prj.chat.service.ChatService;
import com.modu.app.prj.chat.service.ChatVO;
import com.modu.app.prj.chat.service.ChatrDTO;
import com.modu.app.prj.chat.service.ChatrParticiVO;
import com.modu.app.prj.chat.service.ChatrVO;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.prj.service.PrjService;


@Controller
public class ChatController {
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	PrjService prjService;
	
	//이거웹소켓?
	//@MessageMapping("/chat/{chatrNo}") 
	
//	public ChatVO greeting(ChatVO chatVO) throws Exception {
//		Thread.sleep(1000); // simulated delay
//		//return new ChatVO("Hello, " + HtmlUtils.htmlEscape(message.getCntn()) + "!");
//		
//		System.out.println(chatVO);
//		return new ChatVO(HtmlUtils.htmlEscape(chatVO.getCntn()));
//	}
	@MessageMapping("/chat/msg") 
	@SendTo("/sub/chat/{chatrNo}")
	public void chatMessage(ChatVO chatVO) throws Exception {
	    // 메시지 처리 로직...
		
	    // 클라이언트로부터 받은 메시지를 다시 /sub/chat 주제로 발행
		messagingTemplate.convertAndSend("/sub/chat/" + chatVO.getChatrNo(), chatVO);
	}

	
	//채팅방으로이동
	@GetMapping("/chat/{chatrNo}") 
	public String goChatPage(@PathVariable String chatrNo, Model model, ChatrParticiVO cptvo, HttpSession session) {
		
		//채팅세션`
		String prjParticiUniNo = (String) session.getAttribute("particiMembUniNo");
		cptvo.setChatrNo(chatrNo);
		cptvo.setParticiMembUniNo(prjParticiUniNo);
		ChatrParticiVO vo = chatService.chatSession(cptvo);
		session.setAttribute("chatrNo", chatrNo);
		session.setAttribute("chatParticiMembUniNo", vo.getChatParticiMembUniNo());
		
		model.addAttribute("info", vo);
		return "chat/chat";
	}

	//채팅방생성폼
	@GetMapping("makeChatr") 
	public String makeChatrForm(Model model, HttpSession session) {
		String prjUniNo = (String) session.getAttribute("prjUniNo");
		model.addAttribute("membList", postService.selectCallMembPub(prjUniNo));
		return "chat/makeChatr";
	}
	
	//채팅방만들기 + 참여멤버추가
	@PostMapping("makeChatr")
	@ResponseBody
	public ChatrDTO makeChatr(@RequestBody ChatrDTO chatrDTO, HttpSession session) {
		
		//채팅만드는사람
		String prjUniNo = (String) session.getAttribute("prjUniNo");
		String particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
		
		//채팅방만든사람도 참여멤버리스트에 넣음
		chatrDTO.getParticiMembUniNos().add(particiMembUniNo);
		
		//채팅방 INSERT 시 필요한 VO
		ChatrVO chatrVO = new ChatrVO();
		chatrVO.setPrjUniNo(prjUniNo);
		//채팅방생성
		chatService.makeChatr(chatrVO);
		
		//리턴용 채팅방번호
		chatrDTO.setChartNo(chatrVO.getChatrNo());
		
		//참여자 수만큼 참여테이블에 INSERT
		List<String> membList = chatrDTO.getParticiMembUniNos();
		for(String memb : membList) {
			ChatrParticiVO charMem = new ChatrParticiVO();
			charMem.setParticiMembUniNo(memb); //프로젝트참여자
			charMem.setChatrNo(chatrVO.getChatrNo()); //채팅방번호
			charMem.setChatrNm(chatrDTO.getChartNm()); //채팅방이름(생성자가정함)
			//참여테이블에 INSERT
			chatService.insertChatMemb(charMem);
		}
		return chatrDTO;
	}
	
	//현재참여중인채팅방리스트
	@GetMapping("chatrList")
	@ResponseBody
	public List<ChatrVO> chatRoomList(String particiMembUniNo, HttpSession session){
		particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
		return chatService.chatRoomList(particiMembUniNo);
	}
	
	//채팅메세지insert
	@PostMapping("chatMsg")
	@ResponseBody
	public ChatVO insertChat(@RequestBody ChatVO chatVO, HttpSession session) {
		String chatrNo = (String) session.getAttribute("chatrNo");
		String chatParticiMembUniNo = (String) session.getAttribute("chatParticiMembUniNo");
		
		chatVO.setChatrNo(chatrNo);
		chatVO.setChatParticiMembUniNo(chatParticiMembUniNo);
		
		System.out.println(chatVO);
		chatService.insertChat(chatVO);
		return chatVO;
	}
	
	@GetMapping("msgList/{chatrNo}")
	@ResponseBody
	public List<ChatVO> chatMessageList(@PathVariable String chatrNo){
		return chatService.chatMessageList(chatrNo);
	}
}
