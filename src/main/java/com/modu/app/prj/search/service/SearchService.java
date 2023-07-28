package com.modu.app.prj.search.service;

import java.util.List;

public interface SearchService {
	
	// 파일, 채팅, 댓글 즐겨찾기 리스트
	public List<SearchVO>BpList(SearchVO vo);
	
	//참여하고 있는 프로젝트에 참여하고있는 모든 채팅방의 채팅 리스트
	public List<SearchVO>chatList(SearchVO vo);
}
