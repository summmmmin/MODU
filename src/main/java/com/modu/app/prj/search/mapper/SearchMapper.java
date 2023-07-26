package com.modu.app.prj.search.mapper;


import java.util.List;

import com.modu.app.prj.search.service.SearchVO;
public interface SearchMapper {
	
	//파일, 채팅, 댓글 즐겨찾기 리스트
	public List<SearchVO>BpList(SearchVO vo);
	
}
