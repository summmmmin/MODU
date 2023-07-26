package com.modu.app.prj.search.service;
import java.util.Date;

import lombok.Data;

@Data
public class SearchVO {
	
	//게시글 고유번호
	private String postUniNo;
	
	//게시판 고유번호
	private String brdUniNo;
	
	//게시글 제목
	private String ttl;
	
	//참여자
	private String cm;
	
	//참여자 고유번호
	private String particiMembUniNo;
	
	//댓글 참여자 알람여부
	private String replyArmYn;
	
	//게시판 알람여부
	private String notiYn;
	
	//채팅 날짜
	private Date writeDt;
	
	//작성자
	private String nnm;
	
	//채팅 번호
	private int chatNo;
	
	//채팅방 번호
	private String chatrNo;
	
	//채팅 참여자 고유 번호
	private String chatParticiMembUniNo;
	
	//채팅내용
	private String cntn;
	
	
}
