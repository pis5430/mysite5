package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트 가져오기
	public List<BoardVo>  boardList() {
			
		System.out.println("dao getBoardList");
		return sqlSession.selectList("board.boardList");		
	}
	
	//게시글 등록
	public void boardInsert(BoardVo boardVo) {
		System.out.println("dao write boardVo :"+boardVo );
		
		sqlSession.insert("board.boardInsert",boardVo);
	}
		
		

}
