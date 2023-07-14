package com.modu.app.prj.board.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.board.mapper.BoardMapper;
import com.modu.app.prj.board.service.BoardService;
import com.modu.app.prj.board.service.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> BoardList() {
		return boardMapper.BoardList();
	}
	
	@Override
	public BoardVO GetBoard(BoardVO vo) {
		return boardMapper.BoardGet();
	}
	
	@Override
	public BoardVO InsertBoard(BoardVO vo) {
		return boardMapper.InsertBoard();
	}
}
