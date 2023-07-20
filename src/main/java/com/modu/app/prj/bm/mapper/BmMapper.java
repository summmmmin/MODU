package com.modu.app.prj.bm.mapper;

import java.util.List;

import com.modu.app.prj.bm.service.BmVO;

public interface BmMapper {

	public List<BmVO> BmList(BmVO vo);
	
	public int bmInsert(BmVO vo);
	
}
