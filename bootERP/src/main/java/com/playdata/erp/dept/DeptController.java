package com.playdata.erp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.playdata.erp.dto.DeptDTO;


@Controller
public class DeptController {
	
	DeptService service;

	public DeptController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public DeptController(DeptService service) {
		super();
		this.service = service;
	}
	
	
	//insert를 위해 view를 볼 수 있는 method 팔요.
	@GetMapping("/dept/register")
	public String showPage() {
		return "dept/dept_register";
	}
	
	
	//view에 입력한 data르르 DB에 insert하기 위한 method
	@PostMapping("/dept/register")
	public String insert(DeptDTO dept) {
		System.out.println(dept);
		service.insert(dept);
		
		//기본이 forward, redirect를 하고싱은 경으 view(tiles에 등록한 view 이름, controller 요청 path)
		return "redirect:/index.html";
	}

	//DBMS에서 저장된 data를 조회하기 위한 method
	@GetMapping("/dept/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("dept/list");
		List<DeptDTO> list = service.select();
		System.out.println(list);
		mav.addObject("deptlist",list);
		return mav;
	}
	
	@GetMapping("/dept/getdept")
//	public ModelAndView read(String deptno) {
//		ModelAndView mav= new ModelAndView("dept/read");
//		mav.addObject("dto",service.read(deptno));
//		return mav;
//	}	           
	public String getDept(String deptno, String cmd, Model model) {
		DeptDTO dept= service.read(deptno);
		String path="";
		if(cmd.equals("view")) {
			path="dept/read";
		}else {
			path="dept/update";
		}
		model.addAttribute("dto",dept);
		return path;
	}
	
	
	@GetMapping("/dept/delete")
	public String delete(String deptno) {
		service.delete(deptno);
		return "redirect:/dept/list";
	}
	
	@PostMapping("/dept/update")
	public String update(DeptDTO dept) {
		service.update(dept);
		return "redirect:/dept/list";
	}
}
