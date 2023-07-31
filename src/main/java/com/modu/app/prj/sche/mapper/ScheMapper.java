package com.modu.app.prj.sche.mapper;

import java.util.List;

import com.modu.app.prj.sche.service.ScheVO;

public interface ScheMapper {
	//프로젝트 리스트
	public List<ScheVO> scheList(String prj);
}
