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

	@Override
	public int setPrjNm(PrjVO vo) {
		return prjMapper.updatePrjNm(vo);
	}

	@Override
	public int delPrj(String prjNo) {
		return prjMapper.deletePrj(prjNo);
	}

	@Override
	public int setGrade(PrjVO vo) {
		return prjMapper.updateGrade(vo);
	}

	@Override
	public int kickPrjParti(PrjVO vo) {
		return prjMapper.kickPrjParti(vo);
	}

	@Override
	public PrjVO getMemInfo(PrjVO vo) {
		return prjMapper.selectMemInfo(vo);
	}

	@Override
	public int getPrjMemCnt(String prjUniNo) {
		return prjMapper.selectPrjMemCnt(prjUniNo);
	}

	@Override
	public PrjVO getPrjInfo(String prjUniNo) {
		return prjMapper.selectPrjInfo(prjUniNo);
	}

	@Override
	public int setPrjSubsp(PrjVO vo) {
		return prjMapper.updatePrjSubsp(vo);
	}

	@Override
	public int getBrdCnt(String prjUniNo) {
		return prjMapper.selectBrdCnt(prjUniNo);
	}

	@Override
	public int getChatrCnt(String prjUniNo) {
		return prjMapper.selectChatrCnt(prjUniNo);
	}

	@Override
	public List<PrjVO> getPrjCnt(PrjVO vo) {
		return prjMapper.selectPrjCnt(vo);
	}
}
