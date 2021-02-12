package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value="/api/guest")
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	//전체 리스트 가져오기 (ajax)
	//	@ResponseBody를 넣어줘야 body에서 찾음 ,이걸 안넣어주면 jsp를 찾게됨
	@ResponseBody 
	@RequestMapping(value = "/list")
	public List<GuestBookVo> list() {
		System.out.println("ApiGuestBookController /ajaxList");

		return guestBookService.addList();
	}
	
	
	//글작성(ajax)
	@ResponseBody
	@RequestMapping(value = "/write")
	public GuestBookVo write(@ModelAttribute GuestBookVo guestBookVo) {
		
		System.out.println("ApiGuestBookController/write");
		System.out.println(guestBookVo);
		
		//guestBookService.add(guestBookVo); //인서트, 저장됨
		
		//입력된 vo전달하고 저장vo를 받아야함
		GuestBookVo vo = guestBookService.writeResultVo(guestBookVo);//인서트 기능
		
		
		return vo;
	}
	
	//글작성(ajax-json)
	@ResponseBody
	@RequestMapping(value = "/write2")
	public GuestBookVo write2(@RequestBody GuestBookVo guestBookVo) { //body에 있는 데이터를 받아줌 
		//@ModelAttribute 와 @RequestBody 를 구분해서 잘 써야함 
		
		System.out.println("ApiGuestBookController/write2");
		System.out.println(guestBookVo);
		
		//guestBookService.add(guestBookVo); //인서트, 저장됨
		
		//입력된 vo전달하고 저장vo를 받아야함
		GuestBookVo vo = guestBookService.writeResultVo(guestBookVo);//인서트 기능
		
		
		return vo;
	}
	
	//글삭제
	@ResponseBody
	@RequestMapping(value = "/delete")
	public int guestDelete(@ModelAttribute GuestBookVo guestBookVo) {
		
		System.out.println("ApiGuestBookController/delete");
		System.out.println(guestBookVo);
		
		int count = guestBookService.delete(guestBookVo); //삭제

		
		return count; //삭제
	}
	
	
	
	
	
	

}
