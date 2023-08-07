package com.modu.app.prj.bm.service;


import java.util.Date;

import lombok.Data;

@Data
public class BmVO {
	
	//즐찾번호
	private String bmUniNo;
	
	//즐찾등록날짜
	private Date regDt;
	
	//참여자 버노
	private String particiMembUniNo;
	
	//메세지 버노
	private String postUniNo;
	
	//파일 버노
	private String attNo;
	
	//채팅 버노
	private String chatNo;
	
	//게시판 이름
	private String boardNm;
	
	// 즐겨찾기
	private String brdYn;
	
	//게시판 버노
	private String brdUniNo;
	
	//즐겨찾기 구분 값
	private String division;
	
	//채팅 방 번호
	private String chatrNo;
	
	//닉네임
	private String nnm;
	
	//게시글 제목
	private String ttl;
	
	//닉네임
	private String postNick;
	
	//내용
	private String cntn;
	
	private String chatNick;
	
	private String bmNick;
	
	private String attNick;
	
	private String boardParticiMembUniNo;
	
}
