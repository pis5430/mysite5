package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

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
	public void write(BoardVo boardVo) {
		System.out.println("Service write");		
		boardDao.boardInsert(boardVo);
	}
		
		

}
