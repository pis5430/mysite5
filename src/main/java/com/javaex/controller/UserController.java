package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	//필드
	//@Autowired
	//private UserDao userDao; //원래는 다오에게 직접 일시키면 안됨 
	
	@Autowired
	private UserService userService;
	
	//생성자
	
	//메소드 getter/setter (생략)
	
	//일반메소드
	
	
	//회원가입 폼
	@RequestMapping(value="/joinForm" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String joinForm() {
		System.out.println("/user/joinForm");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/join" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		
		System.out.println("/user/join");
		System.out.println(userVo);
		
		int count = userService.join(userVo);// dao에 바로 연결하지 않고 서비스로
		
		//회원가입 성공, 실패여부 if문
		
		return "user/joinOk";
	}
	
	//로그인 폼
	@RequestMapping(value="/loginForm" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String loginForm() {
		System.out.println("/user/loginForm");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value="/login" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login");		
		System.out.println("login userVo : "+userVo);
		
		UserVo authUser = userService.login(userVo);
		//System.out.println("controller-->" + authUser.toString());
		
		if(authUser == null) {//실패했을때
			//로그인폼 result = fail
			System.out.println("로그인실패");
			return "redirect:/user/loginForm?result=fail";
		}else{//성공했을때			
			System.out.println("로그인 성공-->" + authUser.toString());	
			session.setAttribute("authUser", authUser); //로그인한 정보 header에 표시하기위에 정보 꺼내서야함		
			return "redirect:/";			
		}
	}
	
	
	//로그아웃
	@RequestMapping(value="/logout" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return  "redirect:/";
	}
	
	//수정폼
	@RequestMapping(value="/modifyForm" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String modifyForm(HttpSession session
							 ,Model model) {
		System.out.println("/user/modifyForm");
		
		//세션에 있는 authUser에서 no값을 가져와서 한명의 정보를 불러와야함(id,name,password)
		UserVo authVo = (UserVo)session.getAttribute("authUser"); //no값이랑 name값을 가지고있음				
		//-- 수업중 코드
		//int no =((UserVo)session.getAttribute("authUser")).getNo();
		//UserVo userVo = userDao.serltUser(no);
		
		//데이터 보내줌 --> modifyForm 파일 착각해서 시간을 너무 많이 씀...
		model.addAttribute("userVo",userService.modifyForm(authVo.getNo()));	
		
		return "user/modifyForm";
	}
	
	//수정
	@RequestMapping(value="/modify" , method= {RequestMethod.GET ,RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/modify");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		userVo.setNo(no); //no값을 안보내줘서 수정이 안됬었음
		
		//userDao.update(userVo);//수정
		//System.out.println("modify controller userVo :" + userVo);
		
		int count = userService.modify(userVo);		
		
		//수정된 값을 다른곳에도 불러와야함 (이름)
		//세션에 저장된 authUser값을 불러와서 이름만 변경
		UserVo authVo = (UserVo)session.getAttribute("authUser");
		authVo.setName(userVo.getName());
		
		
		return "redirect:/"; //메인으로 갔을때 이름 변경되어야함
	}
	
	
	

}
