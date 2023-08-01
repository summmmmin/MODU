package com.modu.app.prj.sche.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ScheVO {
	private String scheUniNo;			//일정고유번호
	private String prjUniNo;			//프로젝트 고유번호
	private String title;					//제목
	private String particiMembUniNo; 	//참가자고유번호
	private String cntn;				//설명
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm", timezone = "GMT+9")
	private Date start;					//시작 날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm", timezone = "GMT+9")
	private Date end;					//마감 날짜
	private String scheParticiMembUniNo;			//일정참여 멤버
	private String particiYn;					//참여여부
	private List<String> particiMembUniNos; //참여자들
}
