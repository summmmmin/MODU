package com.modu.app.prj.chat.service;

import lombok.Data;

@Data
public class ChatChmVO {
	//채팅열람여부
	private String particiMembUniNo; //참여자번호
	private String chatrNo; //채팅방번호
	private int chatNo; //채팅메세지번호
	private char cfmYn; //채팅메세지읽음여부
	private String chatParticiMembUniNo; // 채팅참여자고유번호
}

