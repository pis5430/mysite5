package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트 가져오기
	public List<BoardVo> boardList() {
			
		System.out.println("dao boardList");
		return sqlSession.selectList("board.boardList");		
	}
	
	//게시판 리스트 가져오기(키워드)
	public List<BoardVo> boardList2(String keyword) {
			
		System.out.println("dao boardList2");
		System.out.println("keyword : " + keyword);
		
		List<BoardVo> boardList = sqlSession.selectList("board.boardList2" , keyword);		
		System.out.println("boardList : " + boardList);

		
		return boardList;
	}
	
	//게시판 리스트 가져오기(키워드) +페이징 추가
	public List<BoardVo> boardList3(String keyword, int startRNum , int endRNum) {
			
		System.out.println("dao boardList3");
		System.out.println("keyword : " + keyword);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("startRNum", startRNum);
		map.put("endRNum", endRNum);
		
		System.out.println(map);
		
		return sqlSession.selectList("board.boardList3" , map);	
	}
	
	//전체글 갯수 구하기(페이징)
	public int selectToTalCnt(String keyword) {
		System.out.println("dao selectToTalCnt");
		
		return sqlSession.selectOne("board.selectToTalCnt" , keyword );
		
	}
	
	//게시글 등록
	public int boardInsert(BoardVo boardVo) {
		System.out.println("dao write boardVo :"+boardVo );
		
		return sqlSession.insert("board.boardInsert",boardVo);
	}
	
	//게시글 삭제
	public int boardDelete(int no) {
		System.out.println("dao boardDelete no :"+no);
		
		return sqlSession.delete("board.boardDelete",no);
	}
	
	//게시글 읽기
	public BoardVo boardOne(int no) {
		System.out.println("dao boardOne no :"+no);
		
		return sqlSession.selectOne("board.boardOne",no);
	}
	
	//조회수 올리기
	public int boardHit(int no) {
		System.out.println("dao boardDelete no :"+no);
		
		return sqlSession.update("board.hitUpdate",no);
	}
	
	//게시물 수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("dao boardUpdate boardVo :"+boardVo);
		
		 sqlSession.update("board.boardUpdate",boardVo);
	}
	
	
	
	
		
		

}
