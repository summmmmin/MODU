package com.modu.app.prj.arm.service;


import lombok.Data;

@Data
public class ArmVO {

//	ARM_UNI_NO          NOT NULL VARCHAR2(10) 
//	PARTICI_MEMB_UNI_NO NOT NULL VARCHAR2(10) 
//	ARM_TM              NOT NULL DATE         
//	ARM_CFM_YN          NOT NULL CHAR(1)      
//	PRJ_UNI_NO          NOT NULL VARCHAR2(10) 
//	BRD_NO                       VARCHAR2(10) 
//	POST_UNI_NO                  VARCHAR2(10) 
//	CHATR_NO                     VARCHAR2(10) 
//	CHAT_NO                      NUMBER       
//	TODO_NO                      VARCHAR2(10) 
//	SCHE_NO                      VARCHAR2(10) 
	public ArmVO() {
		
	}
	public ArmVO(String particiMembUniNo,String postUniNo) {
		this.particiMembUniNo=particiMembUniNo;
		this.postUniNo=postUniNo;
	}
	
	private String armUniNo;
	
	private String particiMembUniNo;
	
	private String prjParticiMembUniNo;
	
	private String armCfmYn;
	
	private String prjUnuNo;
	
	private String brdNo;
	
	private String postUniNo;
	
	private String chatrNo;
	
	private int chatNo;
	
	private String todoNo;
	
	private String scheNo;
	
}
