package com.playdata.erp.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playdata.erp.board.BoardService;
import com.playdata.erp.dto.BoardDTO;

@Controller
public class IndexController {
	BoardService service;
	
	
	@Autowired
	public IndexController(BoardService service) {
		super();
		this.service = service;
	}

	@GetMapping("/index.html")
	public String main(Model model) {
		List<BoardDTO> list = service.findByCategory("게시판");
		model.addAttribute("boardlist",list);
		return "layout/index";
	}

	@RequestMapping("/emp/login.do")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/menu/board.do")
	public String board(){
		return "menu/board";
	}
	
	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
