package com.modu.app.prj.search.mapper;


import java.util.List;

import com.modu.app.prj.search.service.SearchVO;
public interface SearchMapper {
	
	//참여하고 있는 프로젝트의 게시글 리스트
	public List<SearchVO>BpList(SearchVO vo);
	
	//참여하고 있는 프로젝트의 참여하고있는 모든 채팅방의 채팅 리스트
	public List<SearchVO>chatList(SearchVO vo);
	
}
