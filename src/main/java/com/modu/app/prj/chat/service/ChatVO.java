package com.modu.app.prj.chat.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChatVO {
	//채팅메세지
	private int chatNo; //채팅메세지번호
	private String chatrNo; //채팅방번호
	private String chatParticiMembUniNo; //참여자번호
	private String cntn; //채팅메세지내용
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
	private Date writeDt; //작성일자
	
	private String particiMembUniNo; //프로젝트참여번호
	private String nnm; //닉네임
	
	
	public ChatVO() {}
	
	//롬복있으니까 이거 필요없는 거 아님?
	public ChatVO(String cntn) {
		this.cntn = cntn;
	}
	
}
