package com.modu.app.prj.chat.service;

public interface ChatService {
	
	//채팅방만들기
	public int makeChatr (ChatrVO chartVO);
	
	//채팅참여자insert
	public int insertChatMemb (ChatrParticiVO chatParticiVO);

}
