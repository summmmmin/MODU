package com.modu.app.prj.board.service;

import java.util.List;

import com.modu.app.prj.bm.service.BmVO;

public interface BoardService {

	// 게시판 리스트
	public List<BoardVO> BoardList(BoardVO vo);

	// 게시판 생성
	public int InsertBoard(BoardVO vo);

	// 게시판 삭제
	public String DeleteBoard(String brdUniNo);

	// 게시판 수정
	public int BrdUpdate(BoardVO vo);
	
	//게시판 이름으로 리스트 찾기 - PostControl에 쓰일 List
	public List<BoardVO> brdNm(BoardVO vo);

}
