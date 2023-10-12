package com.playdata.jpatest.springdata.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	// 간단한 test를 위해 모두 생략하고 controller에서 직접 DAO 호출.
	// 원래는 View -> Controller -> Service -> DAO -> Repository 
	BoardDAO dao;
	@Autowired
	public BoardController(BoardDAO dao) {
		super();
		this.dao = dao;
	}
	
	@GetMapping("/jpa/page/list")
	// Spring data paging 처리를 위한 controller
	public String jpaFindAll(Model model, String pageNo) {
		List<BoardEntity> list = dao.findAll(Integer.parseInt(pageNo));
		model.addAttribute("boardlist", list);
		return "jpa/boardlist";
	}
}
