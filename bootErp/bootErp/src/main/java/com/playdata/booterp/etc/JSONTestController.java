package com.playdata.booterp.etc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.playdata.booterp.board.BoardService;
import com.playdata.booterp.dto.BoardDTO;
@RestController
//@Controller
//컨트롤러 내부에서 정의되어 있는 모든 메소드가 json이나 String과 같은 데이터를 리턴하는 메소드이면 상단에 한 번만 정의해서 사용할 수 있다.
//@ResponseBody
@RequestMapping("/json")
public class JSONTestController {
	BoardService service;
	@Autowired
	public JSONTestController(BoardService service) {
		super();
		this.service = service;
	}

	/* @ResponseBody */
	@GetMapping("/getString")
	public String responseString() {
		System.out.println("==============================================");
		return "text data";
	}

	/* @ResponseBody */
	@GetMapping("/getJsonObj")
	public BoardDTO responseObj() {
		System.out.println("restcontroller사용하기~~~~~~~~~~~~~~~");
		return service.getBoardInfo("3");
	}

	/* @ResponseBody */
	@GetMapping(value="/getJsonArr",produces = "application/json;charset=utf-8")
	public List<BoardDTO> responseJsonArr() {
		return service.boardList();
	}
}




