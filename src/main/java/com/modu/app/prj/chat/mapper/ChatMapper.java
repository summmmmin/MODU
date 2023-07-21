package com.modu.app.prj.chat.mapper;

import java.util.List;

import com.modu.app.prj.chat.service.ChatVO;
import com.modu.app.prj.chat.service.ChatrParticiVO;
import com.modu.app.prj.chat.service.ChatrVO;

public interface ChatMapper {
	
	//세션용
	public ChatrParticiVO chatSession(ChatrParticiVO cptvo);
	
	//채팅방만들기
	public int makeChatr (ChatrVO chartVO);
	
	//채팅참여자insert
	public int insertChatMemb(ChatrParticiVO chatParticiVO);
	
	//참여해있는채팅방리스트
	public List<ChatrVO> chatRoomList(String particiMembUniNo);
	
	//채팅메세지insert
	public int insertChat(ChatVO chatVO);
	
	//채팅메세지전체리스트??
	public List<ChatVO> chatMessageList(String chatrNo);
	

}
