package com.modu.app.prj.prj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.prj.mapper.PrjMapper;
import com.modu.app.prj.prj.service.PrjService;
import com.modu.app.prj.prj.service.PrjVO;

@Service
public class PrjServiceImpl implements PrjService {
	@Autowired
	PrjMapper prjMapper;
	
	@Override
	public int insertPrj(PrjVO prjVO) {
		return prjMapper.insertPrj(prjVO);
	}

	@Override
	public List<PrjVO> getPrjList(String membUniNo) {
		return prjMapper.selectPrjList(membUniNo);
	}

	@Override
	public List<PrjVO> getPrjPartiList(PrjVO prjVO) {
		return prjMapper.selectPrjParti(prjVO);
	}

	@Override
	public PrjVO prjSession(PrjVO vo) {
		return prjMapper.prjSession(vo);
	}

	@Override
	public List<PrjVO> grdCmmn() {
		return prjMapper.grdCmmn();
	}
}
