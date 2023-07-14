package com.modu.app.prj.board.mapper;

import java.util.List;

import com.modu.app.prj.board.service.BoardVO;

public interface BoardMapper {

	public List<BoardVO> BoardList();
	
	public BoardVO BoardGet();
	
	public int InsertBoard(BoardVO vo);
	
}
