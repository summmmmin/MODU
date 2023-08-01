package com.modu.app.prj.search.service;

import java.util.List;

public interface SearchService {
	
	//게시글 검색
	public List<SearchVO>BpList(SearchVO vo);
	
	//채팅 검색
	public List<SearchVO>chatList(SearchVO vo);
	
	//파일 검색
	public List<SearchVO>fileList(SearchVO vo);
}
