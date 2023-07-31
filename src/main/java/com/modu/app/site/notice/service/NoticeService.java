package com.modu.app.site.notice.service;

import java.util.List;

public interface NoticeService {
	
	//사이트 공지사항 리스트
	public List<NoticeVO> noticeList();
	
	//사이트 공지사항 인서트
	public int insertNotice(NoticeVO noticeVO);
	
	

}
