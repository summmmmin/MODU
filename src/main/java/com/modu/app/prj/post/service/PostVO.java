package com.modu.app.prj.post.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostVO {
	private String postUniNo; // 게시글고유번호
	private String brdUniNo; // 게시판고유번호
	private String ttl; // 제목
	private String cm; // 내용
	private String particiMembUniNo;//작성자
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date writeDt; // 작성일자
	private String postTagArm; // 게시글태그알림
	private List<String> postTagArmList; // 게시글태그알림
	private char replyArmYn; // 댓글알림여부
	private char notiYn; // 공지여부
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date notiRegDt; //공지등록일자
	
	private String boardNm; // 게시판이름
	private String nnm; //닉네임
	private String prjUniNo; //프로젝트고유번호
	private char pubcYn; // 게시판공개여부 //게시판단건조회ㅇㅇ
	
	private String membUniNo; //회원번호
}

