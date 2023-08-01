package com.modu.app.cmmn.mapper;

import java.util.List;

import com.modu.app.cmmn.service.CmmnVO;

public interface CmmnMapper {
	
	//2023-07-23 김동건 
	// 공통 코드 리스트
	public List<CmmnVO> getCmmn(String grpCd);
}
