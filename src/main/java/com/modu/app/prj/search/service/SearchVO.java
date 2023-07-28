package com.modu.app.prj.search.service;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	
	//프로젝트 고유번호
	private String prjUniNo;
	
	//채팅방 이름
	private String chatrNm;
	
	//채팅방 참여자
	private String chatrPartici;
	
}
