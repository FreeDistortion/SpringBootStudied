package com.playdata.thymeleafPro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class ThymeleafLayoutTestController {
	@GetMapping("/fragmenttest")
	public String fragment_test() {
		return "fragment/main";
	}
	@GetMapping("/prdlist")
	public String productlist() {
		return "product/productlist";
	}
	@GetMapping("/mypage")
	public String mypage() {
		return "member/mypage";
	}
	@GetMapping("/prdread")
	public String prdread(Model model, String prdno, String prdname) {
		model.addAttribute("prdno", prdno);
		model.addAttribute("prdname", prdname);
		
		return "product/productRead";
	}
}
