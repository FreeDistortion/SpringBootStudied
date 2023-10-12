package com.playdata.thymeleafPro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/test")
	public String test(Model model) {
		//resources/templates/hello.html
		//                   -------
		//                    뷰이름
		model.addAttribute("msg","spring boot 컨트롤러에서 공유된 데이터");
		return "hello"; 
	}
}
