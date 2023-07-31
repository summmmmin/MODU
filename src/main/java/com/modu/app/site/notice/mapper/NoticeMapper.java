package com.modu.app.site.notice.mapper;

import java.util.List;

import com.modu.app.site.notice.service.NoticeVO;

public interface NoticeMapper {
	
	//사이트 공지사항 리스트
	public List<NoticeVO> noticeList();
	
	//사이트 공지사항 단건조회
	public NoticeVO selectNotice(String noticeUniNo);
	
	//사이트 공지사항 인
	public int insertNotice(NoticeVO noticeVO);
	
	//사이트 공지사항 업데이트
	public int updateNotice(NoticeVO noticeVO);
	
	//사이트 공지사항 삭제
	public int deleteNotice(String noticeUniNo);

}
