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
	public int BrdBmInsert(BmVO vo) {
		int result = 0;
		// 값 조회
		int bmCount = bmMapper.BmCount(vo);
		System.out.println(bmCount);
		System.out.println(vo);
		// 값이 없으면 등록
		if (bmCount == 0) {
			result = bmMapper.BrdBmInsert(vo);
		// 값이 있으면 삭제
		}else {
			result = bmMapper.BmDelete(vo);
		}
		return result;
	}
	
	@Override
	public int BmCount(BmVO vo) {
		return bmMapper.BmCount(vo);
	}
	
	@Override
	public int BmDelete(BmVO vo) {
		return bmMapper.BmDelete(vo);
	}
}
