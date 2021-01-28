package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	//Service에서 dao랑 연결
	
	@Autowired
	private GuestBookDao guestBookDao;
	
	
	//방명록 리스트
	public List<GuestBookVo> addList(){
		System.out.println("Service addList");
		
		return guestBookDao.getGuestList(); 
	}
	
	//등록
	public void add(GuestBookVo guestBookVo) {
		System.out.println("Service add");
		guestBookDao.add(guestBookVo);
	}
	
	
	// 삭제
		public int delete(GuestBookVo guestBookVo) {
			System.out.println("Service delete()");
			
			return guestBookDao.delete(guestBookVo);
		}
	

}
