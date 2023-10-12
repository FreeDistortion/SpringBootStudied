package com.playdata.booterp.etc;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.playdata.booterp.board.BoardService;
import com.playdata.booterp.dto.BoardDTO;

@RestController
public class BoardJSONController {
	BoardService service;

	public BoardJSONController(BoardService service) {
		super();
		this.service = service;
	}
	@GetMapping("/json/categorySearch")
	public List<BoardDTO> categorysearch() {
		return service.findByCategory("경조사");
	}
}
