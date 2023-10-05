package com.playdata.erp.member;

import java.io.FileNotFoundException;
import java.io.IOException;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.playdata.erp.dept.DeptService;
import com.playdata.erp.dto.MemberDTO;
import com.playdata.erp.dto.MemberImgDTO;

@Controller
@RequestMapping("/emp")
// @SessionAttributes("user"): user는 session에 저장하는 attribute name.
// Controller에서 user라는 이름으로 Model object에 저장된 attribute가 있으면 이 attribute를 session에도 저장해준다.
// EL은 page scope -> request scope -> session scope -> application ->null
// @SessionAttributes("user")를 이용해서 session을 사용하는 경우, controller에 @SessionAttributes("user")가 있어야 사용할 수 있다.
// @SessionAttributes("user")가 있기에 model에서 값을 저장하거나 가져와도 session에 있는 값으로 등록되므로 사용이 가능. 만약 다른 Controller에서 사용하는 경우 해당 annotation을 선언하고 사용해야 함.
@SessionAttributes("user")
public class MemberController {

	private static final String String = null;
	MemberService serv;

	public MemberController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public MemberController(MemberService serv) {
		super();
		this.serv = serv;
	}

	@GetMapping("/login")
	public String showLoginPage() {

		return "login";
	}
	
	
//
//	@PostMapping("/login")
//	public String login(MemberDTO loginUser, HttpServletRequest request) {
//		MemberDTO user = serv.login(loginUser);
//		String view;
//		if (user != null) {// login 성공
//			HttpSession session = request.getSession();
//
//			session.setAttribute("user", user);
//			view = user.getMenupath();
//		} else {// 로그인 실패
//			view = "redirect:/emp/login";
//		}
//
//		return view;// login user의 job을 검사해서 해당하는 메뉴가 보일 수 있도록 view 연결
//	}
//
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		if (session != null) {
//			// 사용하돈 session을 메모리에서 제거d
//			session.invalidate();
//		}
//		return "redirect:/emp/login";
//	}
//
//	// Spring에서 제공되는 기능을 이용한 login
//	@PostMapping("/spring/login")
//	public String springlogin(MemberDTO loginUser, Model model) {
//		System.out.println("Hanling session with using @sessionAttributes provided by Spring");
//		MemberDTO user = serv.login(loginUser);
//		String view;
//		if (user != null) {// login 성공
//			model.addAttribute("user", user);
//			view = user.getMenupath();
//		} else {// 로그인 실패
//			view = "redirect:/emp/login";
//		}
//
//		return view;// login user의 job을 검사해서 해당하는 메뉴가 보일 수 있도록 view 연결
//	}
//
//	@GetMapping("/spring/logout")
//	public String springlogout(SessionStatus status) {
//		System.out.println("SPRING LOGOUT");
//		status.setComplete();// session에 저장된 object 제거 - @sessionAttribute로 controller 상단에 선언된 object만 제거
//		return "redirect:/index.html";
//	}
//
//	@GetMapping("/mypage")
//	public String mypage(HttpSession session) {
//		// 나중에는 제일 복잡한 controller가 될 수 있다.
//		// 결재, 스케쥴, 진행 중 업무 등의 처리가 되는 view 추가.
//
//		// login한 사용자 정보 꺼내기
//		MemberDTO user = (MemberDTO) session.getAttribute("user");
//		return user.getMenupath();
//		
//	}
//	
//	@Autowired
//	DeptService deptService;
//	@GetMapping("/insert")
//	public String insertpage(Model model) {
//		model.addAttribute("deptlist", deptService.getDeptName());
//		return "member/insertPage";
//	}
//	
//	@Autowired
//	ImageFileUploadLogic imageFileUploadLogic;
//	@PostMapping("/insert")
//	public String insertmember(MemberDTO member, HttpSession session) throws IllegalStateException, IOException {
//		MultipartFile file = member.getUserImage();
//		String path = WebUtils.getRealPath(session.getServletContext(), "/WEB-INF/upload");
//		MemberImgDTO memberImgDTO=imageFileUploadLogic.uploadIamge(file, path);
//		
//		return "redirect:";
//	}
//	
	
	
}
