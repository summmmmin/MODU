package com.modu.app.prj.chat.service.impl;

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

	@Override
	public int makeChatr(ChatrVO chartVO) {
		return chatMapper.makeChatr(chartVO);
	}

	@Override
	public int insertChatMemb(ChatrParticiVO chatParticiVO) {
		return chatMapper.insertChatMemb(chatParticiVO);
	}

}
