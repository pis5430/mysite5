package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	
	//게시판 리스트
	public List<BoardVo> boardList(){
		System.out.println("Service boardList");
			
		return boardDao.boardList();
	}
		
	//write(게시판 글 등록)
	public int write(BoardVo boardVo) {
		System.out.println("Service write");	
		
		
		return boardDao.boardInsert(boardVo);
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
