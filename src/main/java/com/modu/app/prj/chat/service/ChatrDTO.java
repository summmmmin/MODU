package com.modu.app.prj.chat.service;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatrDTO {
	//채팅방최초생성용DTO

	//private String prjUniNo; //프로젝트번호
	//private String particiMembUniNo; //채팅방생성자
	private String chartNo;
	private String chartNm; // 채팅방이름(일단 생성자가 지정 추후 각자 수정)
	private List<String> particiMembUniNos; //참여자들
	
	   public ChatrDTO() {
	        particiMembUniNos = new ArrayList<>(); // 리스트 초기화
	    }

}
