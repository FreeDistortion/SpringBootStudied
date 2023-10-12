package com.playdata.thymeleafPro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/th")
public class ThymeleafBasicTestController {
	@GetMapping("/text")
	public String basic(Model model) {
		model.addAttribute("msg", "thymeleaf이용하기");
		model.addAttribute("data", "test");
		return "basic/text_test";
	}
	@GetMapping("/formtest1")
	public String formtest1(Model model) {
		model.addAttribute("data", "thymeleaf");
		return "basic/form_test";
	}
	@PostMapping("/formtest1")
	public String formtest1_run(Model model,String thymeleaf) {
		System.out.println("thymleaf파라미터=>"+thymeleaf);
		return "basic/form_test";
	}
	@GetMapping("/oprtest")
	public String oprtest() {
		return "basic/opr_test";
	}
	@GetMapping("/iftest")
	public String iftest(Model model) {
		model.addAttribute("myval",70);
		return "basic/if_test";
	}
	@GetMapping("/sharedObjTest")
	public String foreachtest(Model model) {
		PersonDTO p1 = new PersonDTO("bts1", "1234","진", 95);
		PersonDTO p2 = new PersonDTO("bts2", "1234","슈가", 89);
		PersonDTO p3 = new PersonDTO("bts3", "1234","남준", 84);
		PersonDTO p4 = new PersonDTO("bts4", "1234","뷔", 100);
		List<PersonDTO> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		model.addAttribute("list",list);//검색, 조회
		model.addAttribute("person",p1);//pk로 검색조건을 주고 조회하는 경우
		return "basic/foreach_test";
	}
	@GetMapping("/linktest")
	public String formtest1_run(Model model,String data1,String id) {
		System.out.println("id=>"+id);
		System.out.println("data1=>"+data1);
		System.out.println("==============================================");
		return "redirect:/";
	}
}
