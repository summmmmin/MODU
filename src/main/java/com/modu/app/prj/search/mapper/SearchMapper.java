package com.modu.app.prj.search.mapper;


import java.util.List;

import com.modu.app.prj.search.service.SearchVO;
public interface SearchMapper {
	
	//게시글 리스트
	public List<SearchVO>BpList(SearchVO vo);
	
	//채팅 검색
	public List<SearchVO>chatList(SearchVO vo);
	
	//파일 검색
	public List<SearchVO>fileList(SearchVO vo);
}
