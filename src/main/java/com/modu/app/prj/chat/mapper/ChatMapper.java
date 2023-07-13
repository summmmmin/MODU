package com.modu.app.prj.chat.mapper;

import java.util.List;

import com.modu.app.prj.chat.service.ChatVO;

public interface ChatMapper {
	
	//채팅메세지전체리스트
	public List<ChatVO> chatMassageList(ChatVO chatVO);

}
