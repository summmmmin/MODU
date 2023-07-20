package com.modu.app.prj.chat.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatrDTO {
	//채팅방최초생성용DTO
//	private String chatrNo; //채팅방번호
//	private String prjUniNo; //프로젝트번호
	
	private String chartNm; // 채팅방이름(각자지정)
	private List<String> particiMembUniNos;
	
	   public ChatrDTO() {
	        particiMembUniNos = new ArrayList<>(); // 리스트 초기화
	    }

}
