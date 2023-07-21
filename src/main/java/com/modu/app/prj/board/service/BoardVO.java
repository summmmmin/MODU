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
	
	private String brdYn;
	
	
}
