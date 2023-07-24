package com.modu.app.prj.vote.service;

import java.util.List;

import com.modu.app.prj.board.service.BoardVO;

import lombok.Data;

@Data
public class MyDataModel {

	private VoteVO voteVO;
	private List<VoteVO> chatrList;
    private List<BoardVO> boardList; 
	
}
