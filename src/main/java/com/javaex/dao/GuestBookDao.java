package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//방명록 리스트 가져오기
	public List<GuestBookVo> getGuestList() {
		
		System.out.println("dao getGuestList");
		return sqlSession.selectList("guest.guestList");		
	}
	
	//방명록 등록
	public void add(GuestBookVo guestBookVo) {
		System.out.println("dao add guestBookVo :"+guestBookVo );
		
		sqlSession.insert("guest.add",guestBookVo);
	}
	
	
	//방명록 삭제
	public int delete(GuestBookVo guestBookVo) {
		System.out.println("dao delete");
		
		return sqlSession.delete("guest.delete", guestBookVo);
	}	
	
	
	//ajax 등록 (selectkey)
	public int insertSelectKey(GuestBookVo guestBookVo) {
		System.out.println("dao insertSelectKey guestBookVo :"+guestBookVo );
		
		
		System.out.println("xml실행전 : " + guestBookVo);
		sqlSession.insert("guest.insertSelectKey",guestBookVo); 
		System.out.println("xml실행후 : " + guestBookVo); //no값이 추가됨
		
		return guestBookVo.getNo();
		
	}
	
	
	//글 한개 가져오기
	public GuestBookVo selectOne(int no) {
		
		System.out.println("dao selectOne()");
		
		return sqlSession.selectOne("guest.selectOne", no);

		
	}
	
	
	
}
