package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	//게시판 리스트
	public List<CommentVo> cBoardList(){
		System.out.println("Service CommentList");
			
		return commentDao.cBoardList();
	}
	
	//write(게시판 글 등록)
	public int write(CommentVo commentVo) {
		System.out.println("Service write");	
		
		
		return commentDao.commentInsert(commentVo);
	}
	
	//댓글 등록
	public int commentWrite(CommentVo commentVo) {
		System.out.println("Service commentowrite");	
		
		commentDao.orderNoUpdate(commentVo); //order_no만 증가시킬 목적
		return commentDao.commentTowInsert(commentVo);
	}
	
	//삭제
	public int commentRemove(int no) {
		System.out.println("Service commentRemove");	
				
		return commentDao.commentDelete(no);
	}	
	
	//게시글 읽기
	public CommentVo read(int no) {
		System.out.println("Service read");	

		commentDao.commentHit(no); //무조건 조회수가 올라감..
		
		return commentDao.commentOne(no);
	}
	
	//수정 불러오기
	public CommentVo modifyRead(int no) {
		System.out.println("Service read");	
		
		return commentDao.commentOne(no);
	}
	
	
	
	//수정
	public void modify(CommentVo commentVo) {
		System.out.println("Service modifyForm");	
		
		 commentDao.commentUpdate(commentVo);

	}

}
