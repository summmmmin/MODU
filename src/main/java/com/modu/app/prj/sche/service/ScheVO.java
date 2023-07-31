package com.modu.app.prj.sche.service;

import java.util.Date;

import lombok.Data;

@Data
public class ScheVO {
	private String scheUniNo;			//일정고유번호
	private String prjUniNo;			//프로젝트 고유번호
	private String title;					//제목
	private String particiMembUniNo; 	//참가자고유번호
	private String cntn;				//설명
	private Date start;					//시작 날짜
	private Date end;					//마감 날짜
	private String[] scheParticiUniNo;			//일정참여 멤버
	private String particiYn;					//참여여부
}
