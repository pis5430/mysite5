package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.CommentService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.CommentVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	
	//댓글 게시판 리스트 출력
	@RequestMapping(value="/list", method= {RequestMethod.GET ,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("컨트롤러 commentlist");
		
		List<CommentVo> cBoardList = commentService.cBoardList();
		System.out.println(cBoardList);
		
		//데이터 보내기
		model.addAttribute("cList", cBoardList);
		
		return "comment/list";
	}

	//글쓰기폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET ,RequestMethod.POST})
	public String writeForm() {
		System.out.println("컨트롤러 writeForm");
			
			
		return "comment/writeForm";
	}
	
	
	//새글쓰기
	@RequestMapping(value="/write", method= {RequestMethod.GET ,RequestMethod.POST})
	public String write(@ModelAttribute CommentVo commentVo,HttpSession session) {
		System.out.println("컨트롤러 write --> boardVo : " + commentVo);
		
		
		//session은 웹의 개념이라서 controller까지 범위에서 사용하는편이 좋음
		int no = ((UserVo)session.getAttribute("authUser")).getNo();		
		commentVo.setUser_no(no);		
		System.out.println("user_no값이 잘 들어갔는지 확인"+commentVo);
		
		
		commentService.write(commentVo);
								
		return "redirect:/comment/list";

	}
		

}
