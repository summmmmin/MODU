package com.modu.app.prj.sche.mapper;

import java.util.List;

import com.modu.app.prj.sche.service.ScheVO;

public interface ScheMapper {
	//일정 리스트
	public List<ScheVO> scheList(String prj);
	
	//일정 등록
	public int scheInsert(ScheVO vo);
	
	//일정 참가자 등록
	public int scheInsertPartici(ScheVO vo);
	
	//일정 조회
	public ScheVO scheInfo(String sno);
	
	//참가자 조회
	public List<ScheVO> schePartici(String sno);
	
	//일정 삭제
	public int scheDelete(String sno);
	
	//제외 참가자목록
	public List<ScheVO> yetPartici(ScheVO vo);
}
