package com.modu.app.prj.sche.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modu.app.prj.sche.mapper.ScheMapper;
import com.modu.app.prj.sche.service.ScheService;
import com.modu.app.prj.sche.service.ScheVO;

@Service
public class ScheServiceImpl implements ScheService{
	
	@Autowired
	ScheMapper scheMapper;
	
	//프로젝트 리스트
	@Override
	public List<ScheVO> scheList(String prj) {
		return scheMapper.scheList(prj);
	}

	@Override
	public String scheInsert(ScheVO vo) {
		int result = scheMapper.scheInsert(vo);
		return vo.getScheUniNo();
	}

	@Override
	public ScheVO scheInfo(String sno) {
		return scheMapper.scheInfo(sno);
	}

	@Override
	public List<ScheVO> schePartici(String sno) {
		return scheMapper.schePartici(sno);
	}

	@Override
	public int scheDelete(String sno) {
		return scheMapper.scheDelete(sno);
	}

	@Override
	public int scheInsertPartici(ScheVO vo) {
		return scheMapper.scheInsertPartici(vo);
	}

	@Override
	public List<ScheVO> yetPartici(ScheVO vo) {
		return scheMapper.yetPartici(vo);
	}

	@Override
	public int scheUpdate(ScheVO vo) {
		return scheMapper.scheUpdate(vo);
	}

	@Override
	public int scheParticiDelete(String sno) {
		// TODO Auto-generated method stub
		return  scheMapper.scheParticiDelete(sno);
	}
	
}
