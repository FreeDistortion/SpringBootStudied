package com.playdata.booterp.etc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.playdata.booterp.board.BoardService;
import com.playdata.booterp.dto.BoardDTO;

@Controller
@RequestMapping("/ajax")
public class AjaxTestController {
	BoardService service;
	@Autowired
	public AjaxTestController(BoardService service) {
		super();
		this.service = service;
	}
	@GetMapping("/view")
	public String view() {
		return "json/ajax";
	}
	@GetMapping("/exam")
	public String exam() {
		return "json/ajax_exam";
	}
	@GetMapping("/noajax")
	public String noajax(String id,Model model) {
		//Ajax로 요청하지 않고 일반적인 방법으로 요청하는 경우
		String msg = "";
		if(id.equals("jang")) {
			msg = "사용불가능아이디";//디비에 이미 저장이 되어 있는 아이디
		}else {
			msg = "사용가능아이디";
		}
		model.addAttribute("msg", msg);
		return "json/ajax";
	}
	@GetMapping(value="/ajaxtest01",produces = "application/text;charset=utf-8")
	@ResponseBody //-@ResponseBody를 정의해야 뷰가 아니라 String으로 response된다.
	public String ajaxtest(String id) {
		String msg = "";
		if(id.equals("jang")) {
			msg = "사용불가능아이디";//디비에 이미 저장이 되어 있는 아이디
		}else {
			msg = "사용가능아이디";
		}
		return msg;
	}
	@GetMapping("/gugu")
	@ResponseBody 
	public String getgugu(String dan) {
		System.out.println("Ajax로 구구단 요청하기");
		String msg = "";
		for(int i=1;i<=9;i++) {
			msg = msg + (dan +"*"+i+"=" +(Integer.parseInt(dan) * i));
		}
		return msg;
	}
	@GetMapping("/exam01")
	@ResponseBody
	public BoardDTO responseObj(String boardno) {
		return service.getBoardInfo(boardno);
	}
	
	@GetMapping("/exam02")
	@ResponseBody
	public List<BoardDTO> responseArr() {
		//내장되어 있는 jackjson-databind라이브러리가 실행되며 자동으로 json araray로 변환되어 response
		//만약 DTO여러 개를 이용해서 JSON Array를 만드는 경우는
		//Jackson라이브러리의 Mapper를 이용해서 직접 json으로 변환하도록 처리한다.
		return service.boardList();
	}
}




















