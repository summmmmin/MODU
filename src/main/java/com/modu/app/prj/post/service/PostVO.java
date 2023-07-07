package com.modu.app.prj.post.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostVO {
	private String postUniqueNumber; // 게시글번호
	private String title; // 제목
	private String content; // 내용
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date writeDate; // 작성일자
	private String fileNumber; // 첨부파일번호
	private String postTagNotification; // 멤버태그호출
	private char commentNotificationStatus; // 댓글알림여부
	private String boardUniqueNumber; // 게시판번호
}

 