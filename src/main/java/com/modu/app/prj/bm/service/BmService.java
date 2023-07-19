package com.modu.app.prj.bm.service;


import java.util.List;

public interface BmService {
	
	//즐겨찾기 전체 조회
	public List<BmVO> BmList(BmVO vo);
	
	//게시판 즐겨찾기 등록
	public int BrdBmInsert(BmVO vo);
	
}
