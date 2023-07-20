package com.modu.app.prj.chat.service;

import lombok.Data;

@Data
public class ChatrParticiVO {
	//채팅참여자
	private String chatParticiMembUniNo; // 채팅참여자고유번호
	private String chartNo; // 채팅방번호
	private String particiMembUniNo; // 프로젝트참여자고유번호
	private char armYn; // 채팅새메세지알림여부
	private String chartNm; // 채팅방이름(각자지정)
}
