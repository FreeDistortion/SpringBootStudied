package com.playdata.hello.thymeleaf;

import java.util.ArrayList;

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
		model.addAttribute("msg","Use Thymeleaf");
		model.addAttribute("data","erp");
		return "basic/text_test";	
	}
	
	@GetMapping("/oprtest")
	public String oprtest() {
		return "basic/opr_test";	
	}


	@GetMapping("/formtest")
	public String formtest(Model model) {
		model.addAttribute("data","thymeleaf");
		model.addAttribute("data2","timeleaf");
		return "basic/form_test";	
	}
	@PostMapping("/formtest")
	public String formtest(Model model, String test1, String thymeleaf) {
		System.out.println("test1: "+test1);
		System.out.println("thymeleaf: "+thymeleaf);
		
		return "basic/form_test";	
	}
	
	@GetMapping("/iftest")
	public String iftest(Model model) {
		model.addAttribute("myval", 98);
		return "basic/if_test";
	}
	
	@GetMapping("/sharedObjTest")
	public String foreachtest(Model model) {
		PersonDTO p1= new PersonDTO("id1", "pass1", "name1",95);
		PersonDTO p2= new PersonDTO("id2", "pass2", "name2",89);
		PersonDTO p3= new PersonDTO("id3", "pass3", "name3",84);
		PersonDTO p4= new PersonDTO("id4", "pass4", "name4",100);
		ArrayList<PersonDTO> people= new ArrayList<>();
		people.add(p1);
		people.add(p2);
		people.add(p3);
		people.add(p4);
		
		model.addAttribute("people",people);
		model.addAttribute("person", p1);
		return "basic/foreach_test";
	}
	
	@GetMapping("/linktest")
	public String form1_run(Model model, String data1, String id) {
		System.out.println("data1="+data1);
		System.out.println("id="+id);
		return "redirect:/index.html";
	}
}
