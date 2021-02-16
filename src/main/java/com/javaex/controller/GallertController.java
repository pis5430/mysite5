package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/gallery")
public class GallertController {

	//전체 리스트 출력
	@RequestMapping("/list")
	public String galleyList() {
		System.out.println("GalleryController galleryList()");
		
		return"/gallery/list";
		
	}
	

	
	
}
