package com.modu.app.prj.vote.service;

import lombok.Data;

@Data
public class VoteDetaVO {

	
	private String voteNo; 					//투표번호
	private String voteDetaNo;				//투표상세번호
	private int cnt;						//투표수
	private String voteItem;				//투표항목
}
