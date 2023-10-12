package com.playdata.booterp.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.playdata.booterp.dto.BoardDTO;
import com.playdata.booterp.dto.BoardFileDTO;
@Repository
public class BoardDAOImpl implements BoardDAO{
	//MyBatis의 핵심클래스를 이용해서 sql문을 실행
	SqlSession sqlSessionTemplate;
	public BoardDAOImpl() {
		
	}
	@Autowired
	public BoardDAOImpl(SqlSession sqlSessionTemplate) {
		super();
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public int insert(BoardDTO board) {
		// TODO Auto-generated method stub
		//mybatis의 insert메소드 내부에서 BoardDTO객체의 getter메소드를 호출해서 데이터를 꺼내서 같은 이름의 파라미터 자리에 매핑
		return sqlSessionTemplate.insert("com.playdata.erp.board.insert",board);
	}
	

	@Override
	public BoardDTO read(String board_no) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("com.playdata.erp.board.read", board_no);
	}

	@Override
	public int update(BoardDTO board) {
		int result = sqlSessionTemplate.update("com.playdata.erp.board.update",board);
		return result;
	}

	@Override
	public int delete(String board_no) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("com.playdata.erp.board.delete", board_no);
	}

	@Override
	public List<BoardDTO> search(String data) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("com.playdata.erp.board.search", data);
	}

	@Override
	public List<BoardDTO> search(String tag, String data) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("tag", tag);
		map.put("data", data);
		List<BoardDTO> list = sqlSessionTemplate.selectList("com.playdata.erp.board.dynamicSearch", map);
		return list;
	}
	//mapper파일에 정의된
		//namespace + id가 sql문을 찾는 키
		//com.playdata.erp.board.selectall
	@Override
	public List<BoardDTO> boardList() {
		return sqlSessionTemplate.selectList("com.playdata.erp.board.selectall");
	}
	@Override
	public List<BoardDTO> findByCategory(String category) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("com.playdata.erp.board.categorySelect", category);
	}
	@Override
	public int insertFile(List<BoardFileDTO> boardfiledtolist) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("com.playdata.erp.board.fileinsert", boardfiledtolist);
	}
	@Override
	public List<BoardFileDTO> getFileList(String boardno) {
		// TODO Auto-generated method stub
		List<BoardFileDTO> filelist = sqlSessionTemplate.selectList("com.playdata.erp.board.fileselect", boardno);
		System.out.println(filelist);
		return filelist;
	}
	@Override
	public BoardFileDTO getFile(BoardFileDTO inputdata) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("com.playdata.erp.board.getfileinfo", inputdata);
	}

}







