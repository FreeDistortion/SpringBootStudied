package com.playdata.erp.board;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playdata.erp.dto.BoardDTO;
import com.playdata.erp.dto.BoardFileDTO;

@Service
public class BoardServiceImple implements BoardService {

	BoardDAO dao;

	@Autowired
	public BoardServiceImple(BoardDAO dao) {
		super();
		this.dao = dao;
	}

	public BoardServiceImple() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 게시글 등록 버튼을 눌렀을 떄 실행될 method
	// - 2개의 작업 처리
	// - TBBOARD table에 글에 대한 일반적인 내용 저장, BOARD_FILE table에 첨부된 file 정보 저징.
	// - service method 한 개에서 dao method 두 개를 호출해야 한다.
	// - service method에 호출되는 두 method가 모두 정상 처리 되어야 DB에 모든 내용을 반영할 수 있도록 처리해야 한다.
	// - 만약 두 작업 중 하나의 작업만 처리되고 오류가 발생하면 진행된 작업이 모두 취소되도록(ROLLBACK) 처리해야 한다.
	// ------------> Transaction 처리를 반드시 해야 한다.
	@Override
	public int insert(BoardDTO board, List<BoardFileDTO> boardfiledtolist) {
		// TODO Auto-generated method stub
		dao.insert(board);
		dao.insertFile(boardfiledtolist);
		return 0;
	}

	@Override
	public List<BoardDTO> boardList() {
		// TODO Auto-generated method stub
		return dao.boardList();
	}

	@Override
	public BoardDTO getBoardInfo(String board_no) {
		// TODO Auto-generated method stub
		return dao.read(board_no);
	}

	@Override
	public int update(BoardDTO board) {
		// TODO Auto-generated method stub
		return dao.update(board);
	}

	@Override
	public int delete(String board_no) {
		// TODO Auto-generated method stub
		return dao.delete(board_no);
	}

	@Override
	public List<BoardDTO> search(String data) {
		// TODO Auto-generated method stub
		return dao.search(data);
	}

	@Override
	public List<BoardDTO> search(String tag, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	// 전체 게시글 조회와 category별 조회를 하나의 service method에서 처리.
	@Override
	public List<BoardDTO> findByCategory(String category) {
		// TODO Auto-generated method stub
		List<BoardDTO> list = null;
		if (category != null) {
			if (category.equals("all")) {
				list = dao.boardList();
			} else {
				list=dao.findByCategory(category);
				
			}
		}
		return list;
	}

	@Override
	public List<BoardFileDTO> getFileList(String boardno) {
		return dao.getFileList(boardno);
	}

	@Override
	public BoardFileDTO getFile(BoardFileDTO inputdata) {
		return null;
	}

}
