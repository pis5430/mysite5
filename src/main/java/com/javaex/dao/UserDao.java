package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 --> 회원가입 저장
	public int insert(UserVo userVo) {
		System.out.println("dao --> insert");
		System.out.println(userVo);
		
		return sqlSession.insert("user.insert", userVo);
	}
	
	//로그인 --> 회원정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("dao --> login");
		System.out.println(userVo);
				
		return sqlSession.selectOne("user.selectUser", userVo);
	}

}