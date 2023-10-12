package com.playdata.booterp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playdata.booterp.dto.BoardDTO;
import com.playdata.booterp.dto.BoardFileDTO;
@Service
public class BoardServiceImpl implements BoardService {
	BoardDAO dao;
	@Autowired
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}
	//게시글등록버튼을 눌렀을때 실행될 메소드
	//- 두 개의 작업을 처리
	//- tbboard테이블에 글에 대한 일반적인 내용을 저장, board_file테이블에 첨부된 파일의 정보를 저장
	//- 서비스메소드 한 개에서 dao메소드 두 개를 호출해야 한다.
	//- 서비스메소드에 호출되는 두 개의 메소드가 모두 정상처리되어야 db에 모든 내용을 반영할 수 있도록 처리해야 한다.
	//- 만약 두 작업 중 하나의 작업만 처리되고 오류가 발생하면 진행된 작업이 모두 취소되도록 처리해야 한다.
	//==================> 트랜잭션처리를 반드시 해야 한다.
	@Override
	public int insert(BoardDTO board,List<BoardFileDTO> boardfiledtolist) {
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
	//전체게시글조회와 카테고리별 조회를 하나의 서비스메소드에서 처리하기
	@Override
	public List<BoardDTO> findByCategory(String category) {
		List<BoardDTO> list = null;
		if(category!=null) {
			if(category.equals("all")) {
				list = dao.boardList();
			}else {
				list = dao.findByCategory(category);
			}
		}
		return list;
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
		return dao.search(tag, data);
	}
	@Override
	public List<BoardFileDTO> getFileList(String boardno) {
		// TODO Auto-generated method stub
		return dao.getFileList(boardno);
	}
	@Override
	public BoardFileDTO getFile(BoardFileDTO inputdata) {
		// TODO Auto-generated method stub
		return dao.getFile(inputdata);
	}


}
