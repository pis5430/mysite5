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
	public List<CommentVo>  commentList() {
			
		System.out.println("dao commentList");
		return sqlSession.selectList("comment.commentList");		
	}
	
	//게시글 등록
	public int commentInsert(CommentVo commentVo) {
		System.out.println("dao commentInsert commentVo :"+commentVo );
		
		return sqlSession.insert("comment.firstCommentInsert",commentVo);
	}
	
	//댓글 등록
	public int commentTowInsert(CommentVo commentVo) {
		System.out.println("dao commentTwoInsert commentVo :"+commentVo );
		
		return sqlSession.insert("comment.secondCommentInsert",commentVo);
	}
	
	//댓글 삭제
	public int commentDelete(int no) {
		System.out.println("dao commentDelete no :"+no);
		
		return sqlSession.delete("comment.commentDelete",no);
	}
	
	//댓글 읽기
	public CommentVo commentOne(int no) {
		System.out.println("dao commentOne no :"+no);
		
		return sqlSession.selectOne("comment.commentOne",no);
	}
	
	//조회수 올리기
	public int commentHit(int no) {
		System.out.println("dao commentHit no :"+no);
		
		return sqlSession.update("comment.commentHitUpdate",no);
	}
	
	//게시물 수정
	public void commentUpdate(CommentVo commentVo) {
		System.out.println("dao commentUpdate commentVo :"+commentVo);
		
		 sqlSession.update("comment.commentUpdate",commentVo);
	}
	
	//order_no 올리기
	public void orderNoUpdate(CommentVo commentVo) {
		System.out.println("dao orderNoUpdate commentVo :"+commentVo);
		
		 sqlSession.update("comment.orderNoUpdate",commentVo);
	}

}
