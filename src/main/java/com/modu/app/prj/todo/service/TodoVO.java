package com.modu.app.prj.todo.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TodoVO {
	private String todoUniNo;				//할일번호
	private String prjUniNo;				//프로젝트고유번호
	private String mgr;						//담당자
	private String writer;					//작성자
	private String cm;						//참여자
	private String cntn;					//내용
	private String ttl;						//제목
	private String pct;						//진행도
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	@JsonFormat(pattern="yyyy-MM-dd")
	private String frDt;						//할일시작날짜
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private String toDt;						//할일마감날짜
	private String particiMembUniNo;		//참여고유멤버

}
