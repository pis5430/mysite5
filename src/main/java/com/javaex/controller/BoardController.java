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

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	//필드
	@Autowired
	private BoardService boardService;
	
	//생성자
	
	//메소드 getter/setter (생략)
	
	//일반메소드
	
	@RequestMapping(value="/list", method= {RequestMethod.GET ,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("컨트롤러 list");
		
		List<BoardVo> boardList = boardService.boardList();
		System.out.println(boardList);
		
		//데이터 보내기
		model.addAttribute("bList", boardList);
		
		return "board/list";
	}
	
	//글쓰기폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET ,RequestMethod.POST})
	public String writeForm() {
		System.out.println("컨트롤러 writeForm");
			
			
		return "board/writeForm";
	}
	
	//글쓰기(insert기능)
	@RequestMapping(value="/write", method= {RequestMethod.GET ,RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo,HttpSession session) {
		System.out.println("컨트롤러 write --> boardVo : " + boardVo);
		
		/*
		이런 값들은 service에서 해주는게 좋은지?(이동)
		int no = ((UserVo)session.getAttribute("authUser")).getNo();		
		boardVo.setUser_no(no);
		
		System.out.println("user_no값이 잘 들어갔는지 확인"+boardVo);
		*/
		
		boardService.write(boardVo);
								
		return "redirect:/board/list";

	}
		
		
	
	//게시판 읽기
	@RequestMapping(value="/read", method= {RequestMethod.GET ,RequestMethod.POST})
	public String read(@RequestParam("no") int no,
						Model model) {
		System.out.println("컨트롤러 read");
		//게시물 정보 받아옴
		
		model.addAttribute("boardOne", boardService.read(no));
		
		return "board/read";
	}
	
	//수정폼
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET ,RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no,
							Model model) {
		System.out.println("컨트롤러 modifyForm");
		
		
		model.addAttribute("boardOne", boardService.read(no));
		
		return "board/modifyForm";
	}
	
	//수정
	@RequestMapping(value="/modify", method= {RequestMethod.GET ,RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("컨트롤러 modify");
		System.out.println(boardVo);
		
		boardService.modify(boardVo);
		
		
		return "redirect:/board/list";
	}
	
	//삭제
	@RequestMapping(value="/remove", method= {RequestMethod.GET ,RequestMethod.POST})
	public String remove(int no) {
		System.out.println("컨트롤러 remove");
		
		boardService.remove(no);
		
		
		return "redirect:/board/list";
	}
	
	
	
}
