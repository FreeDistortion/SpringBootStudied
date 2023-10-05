package com.playdata.hello.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class ThymeleafLayoutController {
	
	@GetMapping("/fragmenttest")
	public String fragmenttest() {
		return "fragment/main";
	}
	
	@GetMapping("/layout1")
	public String layout1() {
		return "product/productlist";
	}
	
	@GetMapping("/layout2")
	public String layout2() {
		return "product/productread";
	}
	
}
