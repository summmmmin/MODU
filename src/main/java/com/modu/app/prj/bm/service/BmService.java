package com.modu.app.prj.bm.service;


import java.util.List;

public interface BmService {
	
	public List<BmVO> BmList(BmVO vo);
	
	public int bmInsert(BmVO vo);
	
}
