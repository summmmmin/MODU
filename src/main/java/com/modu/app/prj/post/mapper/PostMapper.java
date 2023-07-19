package com.modu.app.prj.post.mapper;

import java.util.List;
import java.util.Map;

import com.modu.app.prj.post.service.MembDTO;
import com.modu.app.prj.post.service.PostVO;
import com.modu.app.prj.user.service.UserVO;

public interface PostMapper {
	
	//로그인멤버=프로젝트참가자확인용(공개게시판) 필요x
	public String isBrdParticiMemb(UserVO userVO);
	
	//전체게시글조회
	public List<PostVO> selectAllPost(String brdUniNo);
	
	//단건조회
	public PostVO selectOnePost(String postUniNo);
	
	//게시글등록, 수정폼용 게시판조회
	public PostVO selectOneBoard(String brdUniNo);
	//등록
	public int insertPost(PostVO postVO);
	
	//수정
	public int updatePost(PostVO postVO);
	
	//삭제
	public int deletePost(String postUniNo);
	
	//댓글알림on/off
	public int replyOnOff(PostVO postVO);
	
	//공지등록on/off
	public int notiOnOff(PostVO postVO);
		
	//상단공지리스트
	public List<PostVO> selectAllNotiPost(String brdUniNo);
	
	//멤버호출용리스트	
	public List<MembDTO> selectCallMembPub(String prjUniNo);
	public List<MembDTO> selectCallMembNonPub(String brdUniNo);
	

}
