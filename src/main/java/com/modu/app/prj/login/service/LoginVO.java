package com.modu.app.prj.login.service;


import java.sql.Date;

import lombok.Data;

@Data
public class LoginVO {
	
//	MEMB_UNI_NO NOT NULL VARCHAR2(10) 
//	ID          NOT NULL VARCHAR2(30) 
//	PWD         NOT NULL VARCHAR2(32) 
//	NM          NOT NULL VARCHAR2(20) 
//	PH_NO                VARCHAR2(13) 
//	SNS         NOT NULL CHAR(1)      
//	QUIT        NOT NULL CHAR(1)      
//	GRD         NOT NULL CHAR(1)      
//	REG_DT      NOT NULL DATE  
	
	//회원 고유번호
	private String membUniNo;
	
	//회원 아이디
	private String id;
	
	//회원 비밀번호
	private String pwd;
	
	//회원 이름
	private String nm;
	
	//회원 전화번호
	private String phNo;
	
	//SNS동의 여부
	private String sns;
	
	//탈퇴여부
	private String quit;
	
	//회원등급
	private String grd;
	
	//회원가입 일자
	private Date regDt;
	
}
