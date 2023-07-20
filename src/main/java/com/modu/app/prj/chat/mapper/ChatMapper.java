package com.modu.app.prj.chat.mapper;

import java.util.List;

import com.modu.app.prj.chat.service.ChatVO;
import com.modu.app.prj.chat.service.ChatrVO;

public interface ChatMapper {
	
	//채팅메세지전체리스트??
	public List<ChatVO> chatMessageList(ChatVO chatVO);
	
	//채팅방만들기
	public int makeChatr (ChatrVO chartVO);

}
