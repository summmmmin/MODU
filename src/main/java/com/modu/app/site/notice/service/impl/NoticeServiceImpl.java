package com.modu.app.site.notice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.site.notice.mapper.NoticeMapper;
import com.modu.app.site.notice.service.NoticeService;
import com.modu.app.site.notice.service.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeMapper noticeMapper;

	//사이트 공지사항 리스트
	@Override
	public List<NoticeVO> noticeList() {
		return noticeMapper.noticeList();
	}
	
	//사이트 공지사항 작성
	@Override
	public int insertNotice(NoticeVO noticeVO) {
		return noticeMapper.insertNotice(noticeVO);
	}

}
