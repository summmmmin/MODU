package com.modu.app.prj.vote.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteVO {
	private String voteNumber; 				//투표번호
	private String projectNumber;			//프로젝트번호
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fromDate;					//투표시작날짜
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")		
	private Date toDate;					//투표마감날짜
	private String title;					//투표제목
	private String writer;					//투표 작성자
	private String postUniqueNumber;		//게시글번호
	private String voteItem;				//투표상세
	private int CountVote;					//투표수
	private String memberUniqueNumber;		//멤버고유번호
}
