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
	
	

}
