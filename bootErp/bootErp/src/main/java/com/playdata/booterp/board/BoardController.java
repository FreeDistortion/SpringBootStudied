package com.playdata.booterp.board;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import com.playdata.booterp.dto.BoardDTO;
import com.playdata.booterp.dto.BoardFileDTO;

@Controller
public class BoardController {
	BoardService service;
	FileUploadLogicService fileuploadservice;
	@Autowired
	public BoardController(BoardService service, FileUploadLogicService fileuploadservice) {
		super();
		this.service = service;
		this.fileuploadservice = fileuploadservice;
	}
	//게시글목록보기
	@GetMapping("/board/list")
	public String list(Model model,String category) {
		System.out.println("&&&&&&&&&&&&&&&"+category);
		List<BoardDTO> boardlist =  service.findByCategory(category);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("category", category);
		return "board/boardlist";
	}
	//게시글검색 - title로 검색
	@PostMapping("/board/search")
	public String search(String search,String tag,Model model) {
		List<BoardDTO> boardlist = service.search(tag, search);
		model.addAttribute("boardlist", boardlist);
		return "board/boardlist";
	}
	//게시글등록작업
	@GetMapping("/board/write")
	public String writePage() {
		return "board/board_write";
	}
	@PostMapping("/board/write")
	public String write(BoardDTO board,HttpSession session) throws IllegalStateException, IOException {
		System.out.println("++++++++++++++++"+board);
		//1. MultipartFile정보를 추출
		List<MultipartFile> files=  board.getFiles();
		//2. 업로드될 서버의 경로 추출(업로드될 위치)
		// => 실제 서버의 경로를 추출하기 위해서 Context객체의 정보를 담고 있는 ServletContext객체를 추출
		// => ServletContext가 웹에 운영하는 사이트(프로젝트)에 대한 정보를 담고 있는 객체이고
		//    실제 경로를 구할 수 있는 메소드가 있다. 
		//    스프링 내부에서 처리할 수 있도록 서블릿컨텍스트객체를 만들어서 전달
		//String path =  WebUtils.getRealPath(session.getServletContext(), "/WEB-INF/upload");
		//System.out.println("^^^^^^^^^^^^^^^^^");
		//System.out.println(path);
		//3. 업로드되도록 처리 - 업로드로직을 구현해서 실제 서버가 인식하는 위치에 업로드가 되도록 처리
		List<BoardFileDTO> boardfiledtolist =  fileuploadservice.uploadFiles(files);
		//4. 게시글에 대한 일반적인 내용과 첨부파일이 있는 경우 첨부파일의 정보를 담은 List를 디비에 저장할 수 있도록 넘기기
		service.insert(board,boardfiledtolist);
		return "redirect:/board/list?category=all";
	}
	//게시글상세보기
	@GetMapping("/board/read")
	public String read(String board_no,String cmd, Model model) {
		BoardDTO board =  service.getBoardInfo(board_no);
		List<BoardFileDTO> boardfiledtolist = service.getFileList(board_no);
		String view = "";
		if(cmd.equals("view")) {
			view = "board/board_read";
		}else {
			view = "board/board_update";
		}
		model.addAttribute("board",board);
		model.addAttribute("boardfiledtolist", boardfiledtolist);
		return view;
	}
	//게시글수정하기
	@PostMapping("/board/update")
	public String update(BoardDTO board) {
		service.update(board);
		return "redirect:/board/list?category=all";
	}
	//게시글삭제
	@GetMapping("/board/delete")
	public String delete(String board_no) {
		service.delete(board_no);
		return "redirect:/board/list?category=all";
	}
	//파일다운로드를 위한 컨트롤러
	@GetMapping("/board/download/{id}/{board_no}/{boardFileorder}")
	public ResponseEntity<UrlResource> downloadFile(@PathVariable String id,@PathVariable String board_no,@PathVariable String boardFileorder)
											throws MalformedURLException {
		System.out.println(board_no+","+boardFileorder);
		//BoardFileDTO객체에 다운로드할 파일을 실제 객체로 변환
		//1. 파일을 다운로드 하기 위해서 디비에 저장된 파일의 정보를 가져오기
		//   => 다른 정보가 필요없는 경우는 원본파일명만 파라미터에 넘겨서 작업할 수 있다.
		BoardFileDTO selectfileInfo = 
				service.getFile(new BoardFileDTO(board_no, "", "", boardFileorder));
		//2. BoardFileDTO에서 꺼낸 다운로드할 파일을 객체로 변환하기
		//   => 변환하기 위해서 실제 파일이 저장되어 있는 위치정보를 넘기기		
		UrlResource resource = new UrlResource("file:"
				+fileuploadservice.getUploadpath(selectfileInfo.getStoreFilename()));
		//3. 파일명에 한글이 있는 경우 오류가 발생하지 않도록 처리 - 다운로드되는 파일명
		String encodedFilename = UriUtils.encode(selectfileInfo.getOriginalFilename(), "UTF-8");
		String mycontenttype="attachment; filename=\""+encodedFilename+"\"";
		
		//4. 응답메시지 만들기
		/*
		 * BodyBuilder builder = ResponseEntity.ok(); ResponseEntity<UrlResource>
		 * response = builder.body(resource);
		 */
		return ResponseEntity.ok()
							 .header(HttpHeaders.CONTENT_DISPOSITION,mycontenttype)//브라우저가 다운로드할 수 있도록 설정
				             .body(resource);
	}
}





























