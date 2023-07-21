package com.modu.app.prj.chat.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatrVO {
	//채팅방
	private String chatrNo; //채팅방번호
	private String prjUniNo; //프로젝트번호
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date crtDt; //채팅방개설일자
}
//CHATR_NO   NOT NULL VARCHAR2(10) 
//PRJ_UNI_NO NOT NULL VARCHAR2(10) 
//CRT_DT     NOT NULL DATE         
