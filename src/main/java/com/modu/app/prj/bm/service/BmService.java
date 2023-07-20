package com.modu.app.prj.bm.service;


import java.util.List;

public interface BmService {
	
	//즐겨찾기 전체조회
	public List<BmVO> BmList(BmVO vo);
	
	//게시판 즐겨찾기 등록
	public int BrdBmInsert(BmVO vo);
	
	//즐겨찾기 리스트
	public List<BmVO> BmSelect(BmVO vo);
	
	//즐겨찾기 Count로 비교해서 별 색 유지
	public int BmCount(BmVO vo);
	
}
