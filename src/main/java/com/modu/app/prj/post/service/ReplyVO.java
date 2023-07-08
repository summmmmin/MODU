package com.modu.app.prj.post.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReplyVO {
	private String replyUniqueNumber; // 댓글번호
	private String content; // 내용
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date writeDate; // 작성일자
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date modifyDate; // 수정일자
	private String tagNotification; // 멤버태그호출
	private String postUniqueNumber; // 게시글번호
	private String joinMemberUniqueNumber; // 작성자(프로젝트참여자)

}
