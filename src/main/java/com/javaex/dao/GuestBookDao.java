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
	
	
	
	
}
