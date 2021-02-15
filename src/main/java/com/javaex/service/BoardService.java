package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	
	//게시판 리스트 -기본
	public List<BoardVo> boardList(){
		System.out.println("Service boardList");
			
		return boardDao.boardList();
	}
	
	//게시판 리스트 - 검색기능추가
	public List<BoardVo> boardList2(String keyword){
		System.out.println("Service boardList2");
		//System.out.println("keyword : " + keyword);
		
		List<BoardVo> boardList = boardDao.boardList2(keyword);
			
		return boardList;
	}
	
	//게시판 리스트 -검색+페이징 기능 추가
	public Map<String , Object> boardList3(String keyword , int crtPage){
		System.out.println("Service boardList3");
		//System.out.println("keyword : " + keyword);
		
		//crtPage --> 시작번호 , 끝번호   1 --> 1,10   2--> 21,20  --> 31,30
		
		//리스트 구하기		
		//페이지당 글갯수
		int listCnt = 10;
		
		//현재 페이지	
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1);

		//시작금 번호 startRNum
		int startRNum = (crtPage-1) * listCnt +1;
		
		//끝 글번호 endRNum
		int endRNum = (startRNum+listCnt) - 1;
		
		//System.out.println("keyword : " + keyword);
		//System.out.println("startRNum : " + startRNum);
		//System.out.println("endRNum : " + endRNum);
		
		List<BoardVo> boardList = boardDao.boardList3(keyword, startRNum, endRNum);
		
		//페이징 계산
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//전체 글갯수 구하기
		int totalCount = boardDao.selectToTalCnt(keyword);
		
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;
		
		
		//시작 버튼 번호
		int startPageBtnNo =  endPageBtnNo - (pageBtnCount -1);
		
		//다음버튼 boolean형
		boolean next;
		
		if(endPageBtnNo * listCnt < totalCount) { // 5*10
			next = true;
		}else {
			next = false;
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}
		
		//이전버튼 boolean형
		
	boolean prev;
		
		if(startPageBtnNo != 1) {
			prev = true;
		}else {
			prev = false;
		}
		
		//prev, startPageBtnNo, endPageBtnNo, next --> jsp전달
		
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev );
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		
		return pMap;
	}
		
	//write(게시판 글 등록)
	public int write(BoardVo boardVo) {
		System.out.println("Service write");	
		
		
		return boardDao.boardInsert(boardVo);
	}
	
	//addBoard(페이징을 위한 게시글 추가)
	public int addBoard(BoardVo boardVo) {
		System.out.println("Service addBoard");	
		
		for(int i =1; i<=1234; i++) {
			boardVo.setTitle(i + "번째 글 작성 제목입니다.");
			boardVo.setContent(i + "번째 글 작성 내용입니다.");
			boardDao.boardInsert(boardVo);
		}
		
		
		return 1;
	}
	
	//delete(db느낌) --> remove(cnt,service에서는 이걸로)(게시판 글 삭제)
	public int remove(int no) {
		System.out.println("Service remove");	
				
		return boardDao.boardDelete(no);
	}	
	
	//게시글 읽기
	public BoardVo read(int no) {
		System.out.println("Service read");	
		//게시물 번호로 해당 게시물 정보 불러오기(작성자,조회수,작성일,제목,본문내용)
		//게시물을 볼때마다 조회수 올라가기 (본인은 제외?)
		//int userNo = ((UserVo)session.getAttribute("authUser")).getNo();
		//(userNo)와 게시물 정보의 user no를 비교해서 조회수 올리기
				
		boardDao.boardHit(no); //무조건 조회수가 올라감..
		
		return boardDao.boardOne(no);
	}
	
	//게시글 수정 불러오기
	public BoardVo modifyRead(int no) {
		System.out.println("Service read");	
		
		return boardDao.boardOne(no);
	}
			

	//수정
	public void modify(BoardVo boardVo) {
		System.out.println("Service modifyForm");	
		
		 boardDao.boardUpdate(boardVo);

	}
	
	
		
		

}
