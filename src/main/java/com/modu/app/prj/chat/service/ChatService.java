package com.modu.app.prj.chat.service;

import java.util.List;

public interface ChatService {
	
	//채팅방세션용
	public ChatrParticiVO chatSession(ChatrParticiVO cptvo);
	
	//채팅방만들기
	public int makeChatr (ChatrVO chartVO);
	
	//채팅방참여자INSERT
	public int insertChatMemb (ChatrParticiVO chatParticiVO);

	//현재참여중인채팅방리스트
	public List<ChatrVO> chatRoomList(String particiMembUniNo);
	
	//채팅메세지INSERT
	public int insertChat(ChatVO chatVO);
	
	//채팅메세지리스트
	public List<ChatDTO> chatMessageList(String chatrNo);
	
	//채팅방나가기
	public int leaveChatr (String chatParticiMembUniNo);
	
	//채팅방수정용
	public ChatrParticiVO selectOneChatr (String chatParticiMembUniNo);
	
	//채팅방명변경
	public int changeChatrNm(ChatrParticiVO chatParticiVO);
	
	//채팅방참여자들리스트
	public List<ChatrParticiVO> chatrParticiList(String chatrNo);
	
	//채팅읽음확인용INSERT
	public int insertChatChm(ChatChmVO chatChmVO);
	
	//채팅참여자추가용리스트
	public List<ChatrParticiVO> addChatrParticiList(ChatrParticiVO chatParticiVO);
	
	//채팅읽음업데이트
	public int updateReadChat(ChatChmVO chatChmVO);
}
