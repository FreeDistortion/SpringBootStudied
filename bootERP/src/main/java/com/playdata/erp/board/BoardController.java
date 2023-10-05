package com.playdata.erp.board;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.playdata.erp.dto.BoardDTO;
import com.playdata.erp.dto.BoardFileDTO;

@Controller
public class BoardController {

	BoardService service;
	FileUploadLogicService fileUploadService;

	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public BoardController(BoardService service, FileUploadLogicService fileUploadService) {
		super();
		this.service = service;
		this.fileUploadService = fileUploadService;
	}



	@GetMapping("/board/list")
	public String list(Model model, String category) {
		List<BoardDTO> boardlist = service.findByCategory(category);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("category", category);
		return "board/boardlist";
	}

	@GetMapping("/board/write")
	public String writePage() {

		return "board/writepage";
	}

//	@PostMapping("/board/write")
//	public String write(BoardDTO board, HttpSession session) throws IllegalStateException, IOException {
//		List<MultipartFile> files = board.getFiles();
//		String path = WebUtils.getRealPath(session.getServletContext(), "/WEB-INF/upload");
//		List<BoardFileDTO> boardFileDTOList = fileUploadService.uploadFiles(files, path);
//		service.insert(board,boardFileDTOList);
//		return "redirect:/board/list?category=all";
//	}
 
	@GetMapping("/board/read")
	public ModelAndView read(String board_no, String cmd) {
		ModelAndView mav = null;
		if (cmd.equals("view")) {
			mav = new ModelAndView("board/board_read");
		} else {
			mav = new ModelAndView("board/board_update");
		}

		BoardDTO b = service.getBoardInfo(board_no);
		List<BoardFileDTO> boardFileDTOList= service.getFileList(board_no);
		mav.addObject("board", b);
		mav.addObject("boardFileDTOList", boardFileDTOList);
		return mav;
	}

	@PostMapping("/board/update")
	public String update(BoardDTO board) {
		service.update(board);
		return "redirect:/board/list?category=all";
	}

	@GetMapping("/board/delete")
	public String delete(String board_no) {
		service.delete(board_no);
		return "redirect:/board/list?category=all";
	}

	@PostMapping("/board/search")
	public ModelAndView search(String search, String tag) {
		ModelAndView mav = new ModelAndView("board/search");
		List<BoardDTO> list = service.search(tag, search);
		System.out.println(list);
		mav.addObject("boardlist", list);
		return mav;
	}
}
