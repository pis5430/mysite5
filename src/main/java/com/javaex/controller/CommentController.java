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
		
		List<CommentVo> commentList = commentService.commentList();
		System.out.println(commentList);
		
		//데이터 보내기
		model.addAttribute("cList", commentList);
		
		return "comment/list";
	}

	//새글쓰기폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET ,RequestMethod.POST})
	public String writeForm() {
		System.out.println("컨트롤러 writeForm");
			
			
		return "comment/writeForm";
	}
	
	//댓글쓰기폼
	@RequestMapping(value="/commentForm", method= {RequestMethod.GET ,RequestMethod.POST})
	public String commentForm() {
		System.out.println("컨트롤러 commentForm");
			
			
		return "comment/commentForm";
	}
	
	
	//새글쓰기
	@RequestMapping(value="/write", method= {RequestMethod.GET ,RequestMethod.POST})
	public String write(@ModelAttribute CommentVo commentVo,HttpSession session) {
		System.out.println("컨트롤러 write --> commentVo : " + commentVo);
		
		
		//session은 웹의 개념이라서 controller까지 범위에서 사용하는편이 좋음
		int no = ((UserVo)session.getAttribute("authUser")).getNo();		
		commentVo.setUser_no(no);		
		System.out.println("user_no값이 잘 들어갔는지 확인"+commentVo);

		
		commentService.write(commentVo);
								
		return "redirect:/comment/list";

	}
	
	//댓글쓰기
	@RequestMapping(value="/commentWrite", method= {RequestMethod.GET ,RequestMethod.POST})
	public String commentWrite(@ModelAttribute CommentVo commentVo,HttpSession session) {
		System.out.println("컨트롤러 commentWrite --> commentVo : " + commentVo);
		
		
		//session은 웹의 개념이라서 controller까지 범위에서 사용하는편이 좋음
		int no = ((UserVo)session.getAttribute("authUser")).getNo();		
		commentVo.setUser_no(no);		
		System.out.println("user_no값이 잘 들어갔는지 확인"+commentVo);
		
		
		System.out.println(commentVo);
		
		
		commentService.commentWrite(commentVo);
								
		return "redirect:/comment/list";

	}
	
	//삭제
	@RequestMapping(value="/remove", method= {RequestMethod.GET ,RequestMethod.POST})
	public String remove(int no) {
		System.out.println("컨트롤러 remove");
		
		commentService.remove(no);
		
		
		return "redirect:/comment/list";
	}
	
	//댓글 읽기
	@RequestMapping(value="/read", method= {RequestMethod.GET ,RequestMethod.POST})
	public String read(@RequestParam("no") int no,
						Model model) {
		System.out.println("컨트롤러 read");
		//게시물 정보 받아옴
		
		model.addAttribute("commentOne", commentService.read(no));
		
		return "comment/read";
	}
	
	//수정폼
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET ,RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no,
							Model model) {
		System.out.println("컨트롤러 modifyForm");
		
		
		model.addAttribute("commentOne", commentService.modifyRead(no));
		
		return "comment/modifyForm";
	}
	
	//수정
	@RequestMapping(value="/modify", method= {RequestMethod.GET ,RequestMethod.POST})
	public String modify(@ModelAttribute CommentVo commentVo) {
		System.out.println("컨트롤러 modify");
		System.out.println(commentVo);
		
		commentService.modify(commentVo);
		
		
		return "redirect:/comment/list";
	}

}
