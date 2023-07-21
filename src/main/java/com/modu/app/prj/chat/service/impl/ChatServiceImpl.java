package com.modu.app.prj.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.chat.mapper.ChatMapper;
import com.modu.app.prj.chat.service.ChatService;
import com.modu.app.prj.chat.service.ChatrParticiVO;
import com.modu.app.prj.chat.service.ChatrVO;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	ChatMapper chatMapper;

	//채팅방만들기
	@Override
	public int makeChatr(ChatrVO chartVO) {
		return chatMapper.makeChatr(chartVO);
	}

	//채팅방참여자INSERT
	@Override
	public int insertChatMemb(ChatrParticiVO chatParticiVO) {
		return chatMapper.insertChatMemb(chatParticiVO);
	}

	//현재참여중인채팅방리스트
	@Override
	public List<ChatrVO> chatRoomList(String particiMembUniNo) {
		return chatMapper.chatRoomList(particiMembUniNo);
	}
}
