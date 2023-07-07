package com.modu.app.prj.board.service;


import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private String boardUniqueNumber;
	
	private String boardName;
	
	private String publicStatus;
	
	private Date registerDate;
	
	private String projectIniqueNumber;
	
	private String joinMemberUniqueNumber; 
	
}
