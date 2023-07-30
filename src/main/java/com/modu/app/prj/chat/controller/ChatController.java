package com.modu.app.prj.chat.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.modu.app.prj.chat.service.ChatChmVO;
import com.modu.app.prj.chat.service.ChatDTO;
import com.modu.app.prj.chat.service.ChatService;
import com.modu.app.prj.chat.service.ChatVO;
import com.modu.app.prj.chat.service.ChatrDTO;
import com.modu.app.prj.chat.service.ChatrParticiVO;
import com.modu.app.prj.chat.service.ChatrVO;
import com.modu.app.prj.file.service.FileService;
import com.modu.app.prj.file.service.FileVO;
import com.modu.app.prj.post.service.MembDTO;
import com.modu.app.prj.post.service.PostService;
import com.modu.app.prj.prj.service.PrjService;

@Validated
@Controller
public class ChatController {
	
	// 현재 접속 중인 클라이언트의 세션을 저장하는 Set
    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	PrjService prjService;
	
	//이거웹소켓?
	@MessageMapping("/chat/msg") 
	//@SendTo("/chat/msg/{chatrNo}")
	public void chatMessage(ChatVO chatVO, FileVO fileVO) throws Exception {
	    // 클라이언트로부터 받은 메시지를 다시 /sub/chat 주제로 발행
		messagingTemplate.convertAndSend("/sub/chat/msg/"+chatVO.getChatrNo(), chatVO);
	}
	
	//타이핑중메세지
	@MessageMapping("/chat/typing") 
	//@SendTo("/chat/msg/{chatrNo}")
	public void chatTypingArm(ChatVO chatVO) throws Exception {
	    // 클라이언트로부터 받은 메시지를 다시 /sub/chat 주제로 발행
		// String typingArm = chatVO.getNnm() + "is typing"; //"'닉네임' is typing"
		messagingTemplate.convertAndSend("/sub/chat/"+chatVO.getChatrNo()+"/typing", chatVO);
	}
	
	// 접속 시 세션 추가
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        sessions.add((WebSocketSession) event.getSource());
    }

    // 접속 해제 시 세션 제거
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        WebSocketSession session = (WebSocketSession) event.getSource();
        sessions.remove(session);
    }

    // 채팅방의 접속자 수 업데이트
    private int updateReadCount(String chatrNo) {
        int readCount = sessions.size(); // 참여자 수
        return readCount;
    }
    
	//채팅방으로이동
	@GetMapping("/chat") 
	public String goChatPage(String chatrNo, Model model, ChatrParticiVO cptvo, HttpSession session) {
		
		//채팅세션
		String particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
		cptvo.setChatrNo(chatrNo);
		cptvo.setParticiMembUniNo(particiMembUniNo);
		ChatrParticiVO vo = chatService.chatSession(cptvo);
	
		session.setAttribute("chatrNo", chatrNo);
		session.setAttribute("chatParticiMembUniNo", vo.getChatParticiMembUniNo());
		
		model.addAttribute("info", vo);
		return "chat/chat";
	}
	
	//채팅방생성용멤버리스트
	@GetMapping("chatMembs/{pNum}")
	@ResponseBody
	public List<MembDTO> chatCallMemb(@PathVariable("pNum") String prjUniNo, HttpSession session){
		prjUniNo = (String) session.getAttribute("prjUniNo");
		return postService.selectCallMembPub(prjUniNo);
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
	public FileVO insertChat(@RequestParam("chatrNo") String chatrNo,
				             @RequestParam("chatParticiMembUniNo") String chatParticiMembUniNo,
				             @RequestParam("cntn") String cntn,
				             @Nullable @RequestParam("file") MultipartFile[] file,
							 HttpSession session){
		//String chatrNo = (String) session.getAttribute("chatrNo");
		//String chatParticiMembUniNo = (String) session.getAttribute("chatParticiMembUniNo");
		String particiMembUniNo = (String) session.getAttribute("particiMembUniNo");
		
		ChatVO chatVO = new ChatVO();
		chatVO.setChatrNo(chatrNo);
		chatVO.setChatParticiMembUniNo(chatParticiMembUniNo);
		chatVO.setCntn(cntn);
		
		//채팅(메세지)등록
		chatService.insertChat(chatVO);
		
		//첨부파일DB등록
		FileVO fileVO = new FileVO();
		
		if(file != null) {

			fileVO.setChatNo((long) chatVO.getChatNo());
			fileVO.setParticiMembUniNo(particiMembUniNo);
			fileVO = fileService.insertFile(file, fileVO);
			
			//첨부파일 있을 때 첨부파일 다운로드 링크 자체를 다음 채팅 메세지로 등록
			ChatVO chatFile = new ChatVO();
			chatFile.setChatrNo(chatrNo);
			chatFile.setChatParticiMembUniNo(chatParticiMembUniNo);
			chatFile.setCntn("<a href='/modu/files/" + fileVO.getAttNo() + "/download'>" + fileVO.getAttNm() +"</a>");
			chatService.insertChat(chatFile);
			
			//채팅알림테이블에 insert
			List<ChatrParticiVO> membList = chatService.chatrParticiList(chatrNo);
			for(ChatrParticiVO memb : membList) {
				ChatChmVO chatChmVO = new ChatChmVO();
				chatChmVO.setParticiMembUniNo(memb.getParticiMembUniNo());
				chatChmVO.setChatrNo(chatrNo);
				chatChmVO.setChatNo(chatFile.getChatNo());
				if(memb.getChatParticiMembUniNo().equals(chatParticiMembUniNo)) {
					chatChmVO.setCfmYn('Y'); //채팅 쓴 사람
				}else{
					chatChmVO.setCfmYn('N'); //다른 사람
				}
				chatChmVO.setChatParticiMembUniNo(memb.getChatParticiMembUniNo());
				chatService.insertChatChm(chatChmVO);
			}
		}
		
		//채팅알림테이블에 insert
		List<ChatrParticiVO> membList = chatService.chatrParticiList(chatrNo);
		for(ChatrParticiVO memb : membList) {
			ChatChmVO chatChmVO = new ChatChmVO();
			chatChmVO.setParticiMembUniNo(memb.getParticiMembUniNo());
			chatChmVO.setChatrNo(chatrNo);
			chatChmVO.setChatNo(chatVO.getChatNo());
			if(memb.getChatParticiMembUniNo().equals(chatParticiMembUniNo)) {
				chatChmVO.setCfmYn('Y'); //채팅 쓴 사람
			}else{
				chatChmVO.setCfmYn('N'); // 다른 사람
			}
			chatChmVO.setChatParticiMembUniNo(memb.getChatParticiMembUniNo());
			chatService.insertChatChm(chatChmVO);
		}
		
		return fileVO;
	}
	
	//채팅메세지전체리스트
	@GetMapping("msgList/{chatrNo}")
	@ResponseBody
	public List<ChatDTO> chatMessageList(@PathVariable String chatrNo){
		return chatService.chatMessageList(chatrNo);
	}
	
	//채팅방나가기
	@GetMapping("leaveChatr/{chatMemb}")
	@ResponseBody
	public String leaveChatr(@PathVariable("chatMemb") String chatParticiMembUniNo, HttpSession session) {
		chatParticiMembUniNo = (String) session.getAttribute("chatParticiMembUniNo");
		chatService.leaveChatr(chatParticiMembUniNo);
		return chatParticiMembUniNo;
	}
	
	//채팅방조회
	@GetMapping("updateChatr/{chatMemb}")
	@ResponseBody
	public ChatrParticiVO selectOneChatr(@PathVariable("chatMemb") String chatParticiMembUniNo) {
		return chatService.selectOneChatr(chatParticiMembUniNo);
	}
	
	//채팅방명변경
	@PostMapping("updateChatrNm")
	@ResponseBody
	public ChatrParticiVO changeChatrNm(@RequestBody ChatrParticiVO chatParticiVO, HttpSession session) {
		String chatParticiMembUniNo = (String) session.getAttribute("chatParticiMembUniNo");
		chatParticiVO.setChatParticiMembUniNo(chatParticiMembUniNo);
		chatService.changeChatrNm(chatParticiVO);
		return chatParticiVO;
	}
	
	//채팅방참여자들리스트
	@GetMapping("chatMembList/{chatrNo}")
	@ResponseBody
	public List<ChatrParticiVO> chatrParticiList(@PathVariable String chatrNo){
		return chatService.chatrParticiList(chatrNo);
	} 
	
	//채팅참여자추가용리스트
	@GetMapping("addChatMembList/{chatrNo}/{prjUniNo}")
	@ResponseBody
	public List<ChatrParticiVO> addChatrParticiList(@PathVariable String chatrNo, @PathVariable String prjUniNo, HttpSession session){
		//chatrNo = (String) session.getAttribute("chatrNo");
		//prjUniNo = (String) session.getAttribute("prjUniNo");
		//System.out.println(chatrNo);
		//System.out.println(prjUniNo);
		ChatrParticiVO chatParticiVO = new ChatrParticiVO();
		chatParticiVO.setChatrNo(chatrNo);
		chatParticiVO.setPrjUniNo(prjUniNo);
		return chatService.addChatrParticiList(chatParticiVO);
	}
	
	//채팅참여자추가
	@PostMapping("addChatMemb")
	@ResponseBody
	public int addChatMemb(@RequestBody ChatrDTO chatrDTO) {
		String chatrNm = chatrDTO.getChartNm();
		String chatrNo = chatrDTO.getChartNo();
		System.out.println(chatrNo);
		System.out.println(chatrDTO);
		int membCount = 0;
		List<String> membList = chatrDTO.getParticiMembUniNos();
		for(String memb : membList) {
			ChatrParticiVO charMem = new ChatrParticiVO();
			charMem.setParticiMembUniNo(memb); //프로젝트참여자
			charMem.setChatrNo(chatrNo); //채팅방번호
			charMem.setChatrNm(chatrNm); //채팅방이름(초대한사람의채팅방이름)
			//참여테이블에 INSERT
			chatService.insertChatMemb(charMem);
			membCount++;
		}
		return membCount;
	}
	
	//채팅읽음업데이트
	@PostMapping("updateReadChat")
	@ResponseBody
	public int updateReadChat(@RequestBody ChatChmVO chatChmVO, HttpSession session) {
		String chatrNo = (String) session.getAttribute("chatrNo");
		String chatParticiMembUniNo = (String) session.getAttribute("chatParticiMembUniNo");
		
		chatChmVO.setChatParticiMembUniNo(chatParticiMembUniNo);
		chatChmVO.setChatrNo(chatrNo);
		return chatService.updateReadChat(chatChmVO);
	}
}
