package com.modu.app.prj.bm.mapper;


import java.util.List;

import com.modu.app.prj.bm.service.BmVO;
public interface BmMapper {
	
	//게시판 즐겨찾기 등록
		public int BrdBmInsert(BmVO vo);
		
		//게시판 즐겨찾기 Count로 비교해서 별 색 유지
		public int BrdBmCount(BmVO vo);
		
		// 게시판 즐겨찾기 해제
		public int BrdBmDelete(BmVO vo);
		
		//파일, 채팅, 댓글 즐겨찾기 등록
		public int BmInsert(BmVO vo);
		
		//파일, 채팅, 댓글 즐겨찾기 리스트
		public List<BmVO>BmList(BmVO vo);
		
		// 중복 즐겨찾기 방지
		public int BmCount(BmVO vo);
		
		// 즐겨찾기 삭제
		public int BmDelete(BmVO vo);
		
		//즐겨찾기 해제
		public List<String> PostBmList(BmVO vo);
	
	
}
