package com.modu.app.prj.chat.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatrVO {
	//채팅방
	private String chatrNo; //채팅방번호
	private String prjUniNo; //참여자번호
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date crtDt; //채팅방개설일자
}
