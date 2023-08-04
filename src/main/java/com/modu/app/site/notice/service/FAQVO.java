package com.modu.app.site.notice.service;

import lombok.Data;

@Data
public class FAQVO {
	//사이트 FAQ
	private String faqNo; //사이트 FAQ번호
	private String ttl; //사이트 FAQ제목
	private String membUniNo; // 사이트 FAQ 작성자
	private String cntn; //사이트 FAQ 내용	
	private String cate; //FAQ 카테고리
}
