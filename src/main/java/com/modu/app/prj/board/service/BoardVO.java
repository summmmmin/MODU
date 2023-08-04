package com.modu.app.prj.board.service;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	
	//게시판 고유번호
	private String brdUniNo;
	
	//게시판 이름
	private String boardNm;
	
	//게시판 공개여부
	private String pubcYn;
	
	//게시판 생성일
	private Date regDt;
	
	//프로젝트 고유번호
	private String prjUniNo;
	
	//참여자 고유번호
	private String particiMembUniNo;
	
	//공개, 비공개 여부
	private String brdYn;
	
	//회원 고유 번호
	private String membUniNo;
	
	//파일 번호
	private String attNo;
	
	//닉네임
	private String nnm;
	
	//등급
	private String grd;
	
	//참여 여부
	private String particiYn;
	
	//프로젝트 아이디
	private String prjPubcId;
	
	//부서
	private String dept;
	
	//뭐지 이거
	private String pos;
	
	//채팅 참여자 번호
	private String chatParticiMembUniNo;
	
	//채팅방 번호
	private String chatrNo;
	
	// 알람 여부
	private String armYn;
	
	//채팅방 이름
	private String chatrNm;
	
	//비공개 게시판 참여자 번호
	private String boardUniNo;
	
	//참여자들 Insert 할 때 씀 아무튼 씀 !
	private List<String> particiMembUniNos;
	
	//비공개 게시판 참여자 고유번호
	private String boardParticiMembUniNo;
	
	private String postUniNo; // 게시글고유번호
	private String ttl; // 제목
	private String cm; // 내용
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date writeDt; // 작성일자
	private String postTagArm; // 게시글태그알림
	private char replyArmYn; // 댓글알림여부
	private char notiYn; // 공지여부
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date notiRegDt; //공지등록일자
	
	
}
