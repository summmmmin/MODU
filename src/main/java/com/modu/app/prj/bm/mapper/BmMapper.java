package com.modu.app.prj.bm.mapper;

import java.util.List;

import com.modu.app.prj.bm.service.BmVO;

public interface BmMapper {
	
	
	//즐겨찾기 전체조회
	public List<BmVO> BmList(BmVO vo);
	
	//게시판 즐겨찾기 등록
	public int BrdBmInsert(BmVO vo);
	
}
