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

	// 사이트 공지사항 리스트
	@Override
	public List<NoticeVO> noticeList() {
		return noticeMapper.noticeList();
	}

	// 사이트 공지사항 단건조회
	@Override
	public NoticeVO selectNotice(String noticeUniNo) {
		return noticeMapper.selectNotice(noticeUniNo);
	}

	// 사이트 공지사항 인서트
	@Override
	public int insertNotice(NoticeVO noticeVO) {
		return noticeMapper.insertNotice(noticeVO);
	}
	
	
	// 사이트 공지사항 업데이트
	@Override
	public int updateNotece(NoticeVO noticeVO) {
		return noticeMapper.updateNotice(noticeVO);
	}
	
	// 사이트 공지사항 삭제
	@Override
	public int deleteNotice(String noticeUniNo) {
		return noticeMapper.deleteNotice(noticeUniNo);
	}

}
