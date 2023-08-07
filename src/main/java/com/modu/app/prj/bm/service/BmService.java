package com.modu.app.prj.bm.service;

import java.util.List;

public interface BmService {
	
	// 게시판 즐겨찾기 등록
		public int BrdBmInsert(BmVO vo);
		
		// 즐겨찾기 Count로 비교해서 별 색 유지
		public int BrdBmCount(BmVO vo);
		
		// 파일, 채팅, 댓글 즐겨찾기 등록
		public int BmInsert(BmVO vo);
		
		// 파일, 채팅, 댓글 즐겨찾기 리스트
		public List<BmVO>BmList(BmVO vo);
		
		// 중복 즐겨찾기 방지
		public int BmCount(BmVO vo);
		
		// 즐겨찾기 해제
		public int BmDelete(BmVO vo);
	
		public List<String> PostBmList(BmVO vo);
		
		// 비공개 게시판 회원 추방
		public int postParticiMembKick(BmVO vo);

}
