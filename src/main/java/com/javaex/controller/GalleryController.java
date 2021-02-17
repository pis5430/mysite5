package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	//전체 리스트 출력
	@RequestMapping("/list")
	public String galleyList(Model model) {
		System.out.println("GalleryController galleryList()");
		
		List<GalleryVo> galleryList = galleryService.galleryList();
		System.out.println(galleryList);
		
		//데이터 보내기
		model.addAttribute("gList",galleryList);
		
		return"/gallery/list";
		
	}
	
	//파일 업로드
	@RequestMapping(value="/upload", method= {RequestMethod.GET ,RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file ,
						 @ModelAttribute GalleryVo galleryVo,
						 HttpSession session,
						 Model model) {
		
		System.out.println("파일 업로드");
		
		//System.out.println(file.getOriginalFilename());
		//System.out.println(file.getSize());
		
		//galleryVo안에 user_no넣어주기
		galleryVo.setUser_no(((UserVo)session.getAttribute("authUser")).getNo());
		
		System.out.println("컨트롤러 vo확인"+galleryVo);
		
	 	String saveName = galleryService.restore(file , galleryVo);
		//model.addAttribute("saveName",saveName);
	 	
		return "redirect:/gallery/list";
	}
	
	//갤러리 이미지 1개만 불러오기
	@ResponseBody
	@RequestMapping(value="/view", method= {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo view(@RequestParam("no") int no) {
		System.out.println("컨트롤러 view 이미지 1개 불러오기 no : " + no);

		GalleryVo galleryVo = galleryService.gallerySelectOne(no);
		System.out.println(galleryVo );
		
		return galleryVo ;
	}
	
	
	//갤러리 삭제
	@ResponseBody
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public int galleryDelete(@RequestParam("no") int no) {
		System.out.println("컨트롤러 galleryDelete 이미지 1개 삭제 no : " + no);
		
		//삭제
		int count = galleryService.galleryDelete(no);
		
		return count;
	}
	
	

	
	
}
