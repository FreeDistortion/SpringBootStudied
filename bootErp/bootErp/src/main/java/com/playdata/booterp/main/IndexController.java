package com.playdata.booterp.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playdata.booterp.board.BoardService;
import com.playdata.booterp.dto.BoardDTO;

@Controller
public class IndexController {
	BoardService service;
	@Autowired
	public IndexController(BoardService service) {
		super();
		this.service = service;
	}
	@RequestMapping("/index.html")
	public String index(Model model) {
		//첫 페이지가 로딩될때 필요한 데이터를 가져오기 위해서 서비스 연결(게시판데이터)
		List<BoardDTO> list = service.findByCategory("게시판");
		model.addAttribute("boardlist",list);
		return "layout/indexLayout";
	}
	@RequestMapping("/emp/login.do")
	public String login() {
		return "login";
	}
	@RequestMapping("/menu/board.do")
	public String menuboard() {
		return "menu/board";
	}
}
