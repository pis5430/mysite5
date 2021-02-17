package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 리스트 가져오기
	public List<GalleryVo> galleryList() {
				
	System.out.println("dao galleryList");
	return sqlSession.selectList("gallery.galleryList");	
	
	}
	
	//갤러리 등록
	public void insert(GalleryVo galleryVo) {
		System.out.println("dao insert galleryVo :" + galleryVo);
		sqlSession.insert("gallery.galleryInsert", galleryVo);
	}
	
	//갤러리 글 하나만 조회하기
	public GalleryVo selectOne(int no) {
		System.out.println("dao selectOne no :" + no);
		
		return sqlSession.selectOne("gallery.gallerySelectOne", no);
	}
	
	//갤러리 글 1개 삭제하기
	public int galleryDelete(int no) {
		System.out.println("dao galleryDelete no :" + no);
		
		return sqlSession.delete("gallery.galleryDelete", no);
	}

}
