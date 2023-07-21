package com.modu.app.prj.chat.service;

import java.util.List;

public interface ChatService {
	
	//채팅방만들기
	public int makeChatr (ChatrVO chartVO);
	
	//채팅방참여자INSERT
	public int insertChatMemb (ChatrParticiVO chatParticiVO);

	//현재참여중인채팅방리스트
	public List<ChatrVO> chatRoomList(String particiMembUniNo);
}
