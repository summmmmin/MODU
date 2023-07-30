package com.modu.app.prj.arm.service;


import lombok.Data;

@Data
public class ArmVO {

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
