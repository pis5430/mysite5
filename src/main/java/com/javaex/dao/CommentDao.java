package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;
import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트 가져오기
	public List<CommentVo>  cBoardList() {
			
		System.out.println("dao commentList");
		return sqlSession.selectList("comment.commentList");		
	}
	
	//게시글 등록
	public int commentInsert(CommentVo commentVo) {
		System.out.println("dao commentInsert commentVo :"+commentVo );
		
		return sqlSession.insert("comment.firstCommentInsert",commentVo);
	}

}
