package com.modu.app.prj.todo.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TodoVO {
	private String todoUniNo;				//할일 번호
	private String prj_uni_no;
	private String mgr;
	private String writer;
	private String cntn;
	private int pct;
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date frDt;						//할일시작날짜
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date toDt;						//할일마감날짜
}
