package com.modu.app.prj.bm.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.bm.mapper.BmMapper;
import com.modu.app.prj.bm.service.BmService;
import com.modu.app.prj.bm.service.BmVO;

@Service
public class BmServiceImpl implements BmService {

	@Autowired
	BmMapper bmMapper;

	@Override
	public List<BmVO> BmList(BmVO vo) {
		return bmMapper.BmList(vo);
	}
	
	@Override
	public int bmInsert(BmVO vo) {
		return bmMapper.bmInsert(vo);
	}
	
}
