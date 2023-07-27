package com.modu.app.prj.chat.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatDTO {
	//채팅리스트용
	private int chatNo; //채팅메세지번호
	private String chatrNo; //채팅방번호
	private String chatParticiMembUniNo; //참여자번호
	private String cntn; //채팅메세지내용
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date writeDt; //작성일자
	
	private String particiMembUniNo; // 프로젝트참여자고유번호
	private String nnm; // 프로젝트내 닉네임
	
}
