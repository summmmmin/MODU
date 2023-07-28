package com.modu.app.prj.board.service;


import java.util.List;

import com.modu.app.prj.bm.service.BmVO;

public interface BoardService {
	
	public List<BoardVO> BoardList(BoardVO vo);
	
	public BoardVO BoardGet();
	
	public int InsertBoard(BoardVO vo);
	
	public String DeleteBoard(String brdUniNo);
	
	//게시판 검색
	public BoardVO BrdSearch(BoardVO vo);
}
