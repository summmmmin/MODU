package com.modu.app.site.notice.service;

import java.util.List;

public interface FAQService {
	
	//사이트 FAQ 리스트
	public List<FAQVO> faqList();
	
	//사이트 FAQ 단건조회
	public FAQVO selectFAQ(String faqNo);
	
	//사이트 FAQ 작성
	public int insertFAQ(FAQVO faq);
	
	//사이트 FAQ 업데이트
	public int updateFAQ(FAQVO faq);
	
	//사이트 FAQ 삭제
	public int deleteFAQ(String faqNo);
	
}
