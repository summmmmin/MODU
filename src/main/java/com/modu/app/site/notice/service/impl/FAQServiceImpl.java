package com.modu.app.site.notice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.site.notice.mapper.FAQMapper;
import com.modu.app.site.notice.service.FAQService;
import com.modu.app.site.notice.service.FAQVO;

@Service
public class FAQServiceImpl implements FAQService {

	@Autowired
	FAQMapper faqMapper;
	
	@Override
	public List<FAQVO> faqList() {
		// faq 전체 리스트
		return faqMapper.faqList();
	}

	@Override
	public FAQVO selectFAQ(String faqNo) {
		// faq 단건 상세
		return faqMapper.selectFAQ(faqNo);
	}

	@Override
	public int insertFAQ(FAQVO faq) {
		// faq 작성
		return faqMapper.insertFAQ(faq);
	}

	@Override
	public int updateFAQ(FAQVO faq) {
		// faq 수정
		return faqMapper.updateFAQ(faq);
	}

	@Override
	public int deleteFAQ(String faqNo) {
		// faq 삭제
		return faqMapper.deleteFAQ(faqNo);
	}

}
