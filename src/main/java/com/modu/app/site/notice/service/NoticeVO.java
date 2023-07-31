package com.modu.app.site.notice.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class NoticeVO {
	//사이트 공지사항
	private String noticeUniNo; //사이트 공지사항번호
	private String ttl; //사이트 공지사항제목
	private String membUniNo; // 사이트 공지사항 작성자
	private String cntn; //사이트 공지사항 내용
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ergDt; // 사이트 공지사항 작성일자
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date modDt; // 사이트 공지사항 수정일자
}
