package com.modu.app.prj.vote.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteVO {
	private String voteNo; 					//투표번호
	private String voteDetaNo;				//투표상세번호
	private String voteParticiMembUniNo;	//투표참가멤버고유번호
	private String prjNo;					//프로젝트번호
	private String particiMembUniNo;		//참가멤버고유번호
	private String postUniNo;				//게시글번호
	private String chatNo;					//채팅번호
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date frDt;						//투표시작날짜
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")		
	private Date toDt;						//투표마감날짜
	private String ttl;						//투표제목
	private String voteItem;				//투표상세
	private int cnt;						//투표수
	
	//게시글
	private String cm; 						//게시글내용
	private String brdUniNo;				//게시판고유번호
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")		
	private Date wirteDt;					//작성일자
	
	//게시판
	private String boardNm;					//게시판이름
	private String prjUniNo;				//프로젝트고유번호
	
	//채팅
	private String chartNo;					//채팅방 번호
	private String cntn;					//채팅 내용
	
}
