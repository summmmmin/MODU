package com.modu.app.prj.bm.service;



public interface BmService {
	
	//게시판 즐겨찾기 등록
	public int BrdBmInsert(BmVO vo);
	
	//즐겨찾기 Count로 비교해서 별 색 유지
	public int BmCount(BmVO vo);
	
	//즐겨찾기 해제
	public int BmDelete(BmVO vo);
	
	//파일, 채팅, 댓글 즐겨찾기 등록
	public BmVO BmInsert(BmVO vo);
	
	//파일, 채팅, 댓글 즐겨찾기 리스트
	public BmVO BmSelect(BmVO vo);
}
