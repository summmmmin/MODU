package com.modu.app.prj.board.service;


import java.util.Date;

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
	
	
	
}
