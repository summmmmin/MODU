package com.modu.app.prj.prj.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

// 2023-07-22 하수민 프로젝트 관리 vo (프로젝트, 프로젝트참여멤버, 결제)
@Data
public class PrjVO {	
	
	private String prjUniNo;			//프로젝트 아이디
	private String prjNm;					//프로젝트명
	private String subspYn;			//구독상태
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	private Date exdt;					//구독마감일
	private String payToken;			//결제토큰
	private String payUniNo;			//결제번호
	
	private String particiMembUniNo;	//프로젝트참여 아이디
	private String membUniNo;			//회원번호
	private String attNo;				//프로필
	private String nnm;					//닉네임
	private String grd;					//등급(공통코드 테이블의 코드)
	private String particiYn;			//탈퇴여부
	private String prjPubcId;			//프로젝트내이메일
	private String dept;				//부서
	private String pos;					//직책
	
	private String cd;					//공통코드의 코드이름
	private String cdNo;				//공통코드의 코드이름
	
	private String kickResn;			//탈퇴사유
	
	private int memCnt;					//프로젝트 내 회원수
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	private Date payDt;			//결제일시
	private String payWay;		//결제방식 (현금, 카드)
	private int payAmt;			//결제금액
	private String cardName;	//카드이름
	
	private String fromDt;	//시작 기간
	private String toDt;		//끝
	private int postcnt;	//게시글수
	private int replycnt;	//답글수
	private int chatcnt;	//chat수
}
