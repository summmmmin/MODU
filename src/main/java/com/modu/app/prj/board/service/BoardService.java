package com.modu.app.prj.board.service;


import java.util.List;

public interface BoardService {
	
	public List<BoardVO> BoardList();
	
	public BoardVO GetBoard(BoardVO vo);
	
	public int InsertBoard(BoardVO vo);
	
}
