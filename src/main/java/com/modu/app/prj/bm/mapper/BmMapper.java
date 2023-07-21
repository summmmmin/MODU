package com.modu.app.prj.bm.mapper;


import com.modu.app.prj.bm.service.BmVO;
public interface BmMapper {
	
	//게시판 즐겨찾기 등록
	public int BrdBmInsert(BmVO vo);
	
	//즐겨찾기 Count로 비교해서 별 색 유지
	public int BmCount(BmVO vo);
	
	//즐겨찾기 해제
	public int BmDelete(BmVO vo);

}
