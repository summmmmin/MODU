package com.modu.app.prj.search.service;

import java.util.List;

public interface SearchService {
	
	// 파일, 채팅, 댓글 즐겨찾기 리스트
	public List<SearchVO>BpList(SearchVO vo);
}
