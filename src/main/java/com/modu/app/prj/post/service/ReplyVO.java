package com.modu.app.prj.post.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReplyVO {
	private String replyUniNo; // 댓글번호
	private String cntn; // 내용
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date writeDt; // 작성일자
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modDt; // 수정일자
	private String tagArm; // 멤버태그호출
	private String postUniNo; // 게시글번호
	private String particiMembUniNo; // 작성자(프로젝트참여자)
	
	private String nnm; //닉네임
	private String attNo; // 첨부파일번호
}

