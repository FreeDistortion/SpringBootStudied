package com.playdata.jpatest.springdata.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	BoardRepository repository;

	@Autowired
	public BoardDAO(BoardRepository repository) {
		super();
		this.repository = repository;
	}

	public List<BoardEntity> findAll(int pageNO) {
		// Paging을 위해 설정 정보를 담고있는 object
		PageRequest pageRequest = PageRequest.of(pageNO, 4, Sort.by(Sort.Direction.DESC, "boardNo"));
		Page<BoardEntity> page=repository.findAll(pageRequest);
		return page.getContent();
	}

	public void insert(BoardEntity entity) {
		repository.save(new BoardEntity("Hong", "spring data CRUD", "Auto generation"));
		repository.save(new BoardEntity("So", "Query method", "Query method is usable"));
		repository.save(new BoardEntity("Hwang", "LOL", "LOL"));

	}

	public void update(BoardEntity entity) {

	}
}
