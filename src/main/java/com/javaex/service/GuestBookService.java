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
		
	//atax 글 저장 --> 저장된 글 리턴
	public GuestBookVo writeResultVo(GuestBookVo guestBookVo) {
		System.out.println("Service writeResultVo");
		
		//글 저장
		System.out.println("Service: dao.insertSelectKey 실행전");
		guestBookDao.insertSelectKey(guestBookVo);
		
		System.out.println("Service: dao.insertSelectKey 실행후");
		int no = guestBookVo.getNo();
		
		//방금 저장한 글 조회하기
		//1.글번호?
		//2.최근글 가져온다. 저장하고 최근글이 방금 저장된 글이 아닐수도 있다. 
		//--> 위험 , 동시에 등록할경우 자기가 작성한 글이 아닌게 출력될수도 있다. 
		
		System.out.println(no);
		
		//글1개 가져오기
		return guestBookDao.selectOne(no);
		
	}	
	

}
