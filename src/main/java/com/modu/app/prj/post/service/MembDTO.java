package com.modu.app.prj.post.service;

import lombok.Data;

@Data
public class MembDTO {
	//멤버호출List용
	private String brdUniNo; // 게시판고유번호
	private String prjUniNo; // 프로젝트고유번호
	private char pubcYn; // 게시판공개여부
	private String nnm; // 프로젝트내 닉네임
	private String particiMembUniNo; // 프로젝트참여고유번호
}
