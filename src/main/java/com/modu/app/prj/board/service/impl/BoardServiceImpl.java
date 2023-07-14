package com.modu.app.prj.board.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.board.mapper.BoardMapper;
import com.modu.app.prj.board.service.BoardService;
import com.modu.app.prj.board.service.BoardVO;
import com.modu.app.prj.prj.service.PrjVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> BoardList(String particiMembUniNo) {
		return boardMapper.BoardList(particiMembUniNo);
	}
	
	@Override
	public BoardVO GetBoard(BoardVO vo) {
		return boardMapper.BoardGet();
	}
	
	@Override
	public int InsertBoard(BoardVO vo) {
		return boardMapper.InsertBoard(vo);
	}
}
