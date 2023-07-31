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

	// 게시판 리스트
	@Override
	public List<BoardVO> BoardList(BoardVO vo) {
		return boardMapper.BoardList(vo);
	}

	// 게시판 생성
	@Override
	public int InsertBoard(BoardVO vo) {
		return boardMapper.InsertBoard(vo);
	}

	// 게시판 삭제
	@Override
	public String DeleteBoard(String partici) {
		return boardMapper.DeleteBoard(partici);
	}

	// 게시판 수정
	@Override
	public int BrdUpdate(BoardVO vo) {
		return boardMapper.BrdUpdate(vo);
	}
	
	//PostControl에 쓰일 List
	@Override
	public List<BoardVO> brdNm(BoardVO vo) {
		return boardMapper.brdNm(vo);
	}
	
	//무료 게시판이면 Count 해서 게시판 4개까지 생성 가능
	@Override
	public int BrdCount(String prjNo) {
		return boardMapper.BrdCount(prjNo);
	}
}
