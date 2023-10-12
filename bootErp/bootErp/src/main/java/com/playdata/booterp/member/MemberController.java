package com.playdata.booterp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.playdata.booterp.dto.MemberDTO;

@Controller
@RequestMapping("/emp")
//@SessionAttributes("user") 는 user라는 것은 세션에 저장하는 어트리뷰트명
//컨틀롤러에서 user라는 이름으로 Model객체에 저장된 어트리뷰트가 있으면 이 어트리뷰를 세션에도 저장해준다.
//@SessionAttributes를 이용해서 세션을 사용하는 경우 컨트롤러에 @SessionAttributes("user")가 있어야 사용할 수 있다.
//                                                     -----------------------------
//                                                     model에서 값을 저장하거 가져와도 세션에 있는 값으로 등록되므로 
//                                                     사용이 가능
//                                                     만약 다른 컨트롤러에서 사용하는 경우 컨트롤러 상단에
//                                                     @SessionAttributes("user")를 선언하고 사용해야 한다.
@SessionAttributes(value = {"user","noboard"})
public class MemberController {
	MemberService service;
	@Autowired
	public MemberController(MemberService service) {
		super();
		this.service = service;
	}
	@GetMapping("/login")
	public String login() {
		
		return "emp/login";
	}
	//세션객체를 만들기위해 매개변수에 HttpServletRequest를 추가한다.
	@PostMapping("/login")
	public String login(MemberDTO loginUser,HttpServletRequest request) {
		MemberDTO user = service.login(loginUser);
		System.out.println("user=========>"+user);
		//로그인 성공과 실패에 따라 뷰가 달라질 것이므로 
		String view = "";
		if(user!=null) {//로그인 성공
			//세션을 만들기
			//=> 기존의 사용하던 세션이 있으면 그 세션객체를 리턴하고 없으면 새로만들어서 리턴
			HttpSession session =  request.getSession();
			//세션에 데이터를 공유하기
			session.setAttribute("user", user);
			
			view = "emp/mypage";
		}else {//로그인실패
			view = "redirect:/emp/login";
		}
		return view;//로그인한 사용자의 job을 검사해서 job에 해당하는 메뉴가 보일 수 있도록 뷰를 연결
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if(session!=null) {
			//사용하던 세션을 메모리에서 제거하기
			session.invalidate();
		}
		return "redirect:/emp/login";
	}
	//스프링에서 제공되는 기능을 이용한 로그인
	@PostMapping("/spring/login")
	public String springlogin(MemberDTO loginUser,Model model) {
		System.out.println("스프링이 제공하는  @sessionAttribute를 이용해서 세션을 처리하기");
		MemberDTO user = service.login(loginUser);
		String view = "";
		if(user!=null) {//로그인 성공
			model.addAttribute("user",user);
			System.out.println(user);
			view =  "emp/mypage";
		}else {//로그인실패
			view = "redirect:/emp/login";
		}
		return view;
	}
	//@sessionAttributes를 이용해서 로그인 처리를 한 경우 SessionStatus객체의 메소드를 이용해서 처리한다.
	//세션에 저장된 객체를 제거하기 위한 객체
	@GetMapping("/spring/logout")
	public String springlogout(SessionStatus status) {
		System.out.println("SessionStatus를 이용한 로그아웃처리");
		status.setComplete();//세션에 저장된 객체를 제거 - @sessionAttributes로 컨트롤러 상단에 선언된 객체만 제거
		return "redirect:/index.html";
	}
	@GetMapping("/mypage")
	public String mypage(Model model) {
		//나중에는 제일 복잡한 컨트롤러가 될 수 있다.
		//결재에 대한 진행상황
		//스케쥴표 - 업무스케쥴, 미팅일정, 휴가일정
		//진행중인 메인업무를 할 수 있는 뷰를 추가
		//로그인한 사용자의 정보를 꺼내기
	
		//MemberDTO user = (MemberDTO) session.getAttribute("user");
		model.addAttribute("noboard", "yes");
		return "emp/mypage";
	}
	@GetMapping("/insert")
	public String insertPage() {
		return "member/insertPage";
	}
	@GetMapping("/json/view")
	public String showjsonview() {
		return "json/json";
	}
}

















