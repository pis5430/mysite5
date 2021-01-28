package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value="/guest")
public class GuestBookController {
	//필드
	@Autowired
	private GuestBookService guestBookService;
	
	//생성자
	
	//메소드 getter/setter (생략)
	
	//일반메소드
	
	//addList
	@RequestMapping(value="/addList", method= {RequestMethod.GET ,RequestMethod.POST})
	public String addList(Model model){ //Model도 import
		System.out.println("컨트롤러 addList");
		
		List<GuestBookVo> guestList = guestBookService.addList();
		System.out.println(guestList.toString());
		
		//model
		model.addAttribute("gList", guestList);
		
		
		return "guestbook/addList";
	}
	
	
	//add(등록)
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("컨트롤러 add");

		guestBookService.add(guestBookVo);
		return "redirect:/guest/addList";
	}
	
	//deleteForm(삭제폼)
	@RequestMapping(value="/deleteForm", method={RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("컨트롤러 deleteForm");
		
		return "/guestbook/deleteForm";
	}
	
	//delete(방명록 삭제)
		@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
		public String guestDelete(@ModelAttribute GuestBookVo guestBookVo) {
			System.out.println("컨트롤러 delete");
			
			int count = guestBookService.delete(guestBookVo);
			
			if(count == 1) { //삭제성공
				
				return "redirect:/guest/addList";
			} else { //삭제실패(result=fail로 보내기)	
				//no
				System.out.println(guestBookVo);
				return "redirect:/guest/deleteForm?result=fail&no=" + guestBookVo.getNo();
			}

		}
	
}
