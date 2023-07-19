package com.modu.app.prj.prj.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PrjVO {
//	PRJ_UNI_NO NOT NULL VARCHAR2(10) 
//	PRJ_NM     NOT NULL VARCHAR2(20) 
//	SUBSP_YN   NOT NULL CHAR(1)      
//	EXDT                DATE         
//	PAY_TOKEN           VARCHAR2(30)
	
//	PARTICI_MEMB_UNI_NO NOT NULL VARCHAR2(10) 
//	PRJ_UNI_NO          NOT NULL VARCHAR2(10) 
//	MEMB_UNI_NO         NOT NULL VARCHAR2(10) 
//	ATT_NO                       VARCHAR2(10) 
//	NNM                 NOT NULL VARCHAR2(20) 
//	GRD                          VARCHAR2(10) 
//	PARTICI_YN          NOT NULL CHAR(1)      
//	PRJ_PUBC_ID                  VARCHAR2(30) 
//	DEPT                         VARCHAR2(30) 
//	POS                          VARCHAR2(30) 	
	
	
	private String prjUniNo;			//프로젝트 아이디
	private String prjNm;					//프로젝트명
	private String subspYn;			//구독상태
	@DateTimeFormat(pattern="yyyy-MM-dd")	
	private Date exdt;					//구독마감일
	private String payToken;				//결제토큰
	
	private String particiMembUniNo;			//프로젝트참여 아이디
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
}
