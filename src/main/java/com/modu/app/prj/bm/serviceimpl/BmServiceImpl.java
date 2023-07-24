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
	
	
	//게시판 즐겨찾기 등록
	@Override
	public int BrdBmInsert(BmVO vo) {
		int result = 0;
		// 값 조회
		int BrdbmCount = bmMapper.BrdBmCount(vo);
		System.out.println(BrdbmCount);
		System.out.println(vo);
		// 값이 없으면 등록
		if (BrdbmCount == 0) {
			result = bmMapper.BrdBmInsert(vo);
		// 값이 있으면 삭제
		}else {
			result = bmMapper.BrdBmDelete(vo);
		}
		return result;
	}
	
	// 게시판 즐겨찾기 Count 0이면 등록 1이면 삭제
	@Override
	public int BrdBmCount(BmVO vo) {
		return bmMapper.BrdBmCount(vo);
	}
	
	// 게시판 즐겨찾기 삭제
	@Override
	public int BmDelete(BmVO vo) {
		return bmMapper.BrdBmDelete(vo);
	}

	//파일, 채팅, 댓글 즐겨찾기 등록
	@Override
	public int BmInsert(BmVO vo) {
		return bmMapper.BmInsert(vo);
	}
	
	@Override
	public List<BmVO> BmSelect(BmVO vo) {
		return bmMapper.BmSelect(vo);
	}
}
