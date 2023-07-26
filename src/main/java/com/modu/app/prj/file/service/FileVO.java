package com.modu.app.prj.file.service;

import lombok.Data;

@Data
public class FileVO {
	private String attNo; // 첨부파일고유번호
	private String particiMembUniNo; // 첨부한사람 
	private String attNm; // 첨부파일이름
	private String serverAttNm; // 첨부파일서버이름
	private Long fSize; // 첨부파일크기
	private String ext; // 확장자
	private String postUniNo; // 첨부된게시글번호
	private Long chatNo; // 첨부된채팅번호
	private char dnYn; // 다운로드여부
	private String noticeUniNo; //사이트공지사항
	private String faqNo; //사이트FAQ
	private String todoUniNo; // 할일고유번호	
}
