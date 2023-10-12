package com.playdata.jpatest.entitymanager.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jpatest")
public class PersonController {
	PersonServiceImpl service;
	@Autowired
	public PersonController(PersonServiceImpl service) {
		super();
		this.service = service;
	}
	@GetMapping("/insert")
	public String showView() {
		System.out.println("showview");
		return "jpa/insert";
	}
	@PostMapping("/insert")
	public String insert(PersonEntity dto) {
		service.insert(dto);
		return "redirect:/";
	}
	@GetMapping("/read")
	public String read(String id,String cmd ,Model model) {
		System.out.println(id+","+cmd);
		PersonEntity person =  service.read(id);
		model.addAttribute("person", person);
		String view = "";
		if(cmd.equals("view")) {
			view = "jpa/read";
		}else {
			view = "jpa/update";
		}
		return view;
	}
	@GetMapping("/delete")
	public String delete(String id) {
		service.delete(id);
		return "redirect:/";
	}
	@PostMapping("/update")
	public String update(PersonEntity dto) {
		System.out.println(dto);
		service.update(dto);
		return "redirect:/";
	}
	@GetMapping("/list")
	public String list(Model model) {
		List<PersonEntity> list  = service.list();
		System.out.println(list);
		model.addAttribute("list", list);
		return "jpa/list";
	}
}
