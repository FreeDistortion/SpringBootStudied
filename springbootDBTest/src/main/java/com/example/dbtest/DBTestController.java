package com.example.dbtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DBTestController {
	
	@Autowired
	DBTestDao dbTestDao;
	
	@GetMapping("/jdbctemplatetest")
	public String templateTest() {
		System.out.println("number of dept: "+dbTestDao.jdbcTemplateTest());
		return "redirect:/";
	}
	
	@GetMapping("/mybatistest")
	public String mybatisTest() {
		System.out.println(dbTestDao.select());
		return "redirect:/";
	}
}
