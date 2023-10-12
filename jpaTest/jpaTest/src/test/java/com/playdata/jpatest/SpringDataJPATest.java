package com.playdata.jpatest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.booleanThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.playdata.jpatest.springdata.test.BoardDAO;
import com.playdata.jpatest.springdata.test.BoardEntity;
import com.playdata.jpatest.springdata.test.BoardRepository;

@SpringBootTest
@Transactional
@Rollback(false)
class SpringDataJPATest {
	@Autowired
	BoardRepository repo;
	@Autowired
	BoardDAO dao;

	// call insert method of DAO
	@Test
	@Disabled
	void daoInsertTest() {
		dao.insert(null);

	}

	@Test
	@Disabled
	void find_test() {
//		System.out.println("*****************************************");
//		System.out.println(repo.getClass());
		List<BoardEntity> boardList = repo.findAll();
		print(boardList);
		
		// Sort.by(정렬기준, 정렬할 col name)
		print(repo.findAll(Sort.by(Direction.DESC,"boardNo")));
		
		// id를 기준으로 조회
		// Lists.newArrayList(1L, 2L, 3L)
//		List<Long> list = new ArrayList<Long>();
//		list.add(1L);
//		list.add(2L);
//		list.add(3L);
//		위와 동일
		// findAllById: parameter로 정의한 값들을 PK에서 조회
		print(repo.findAllById(Lists.newArrayList(1L, 2L, 3L)));
		
	}
	
	@Test
	@Disabled
	void insertall() {
		BoardEntity board1 = new BoardEntity("Writer1", "TITLE1", "content1");
		BoardEntity board2 = new BoardEntity("Writer2", "TITLE2", "content2");
		BoardEntity board3 = new BoardEntity("Writer3", "TITLE3", "content3");
		BoardEntity board4 = new BoardEntity("Writer4", "TITLE4", "content4");
		
		// 한꺼번에 insert
		repo.saveAll(Lists.newArrayList(board1, board2, board3, board4));
		
	}
	
	@Test
	@Disabled
	void reatTest() {
		// PK로 조회
		BoardEntity board = repo.findById(1L).get();
		Optional<BoardEntity> board2 = repo.findById(1L);
		System.out.println(board);
		System.out.println(board2);
		
		//개수
		System.out.println("count of record: "+repo.count());
		
		boolean b1 = repo.existsById(1L);
		boolean b2 = repo.existsById(100L);
		System.out.println(b1+", "+b2);
	}

	@Test
	@Disabled
	public void update() {
		BoardEntity board = repo.findById(1L).get();
		System.out.println("1st Record => "+board);
		board.setTitle("Edited TITLE");
		
		// save method는
		// 새로 만들어 작업하는 경우, insert.
		// 조회한 object의 setter method를 이용해 값을 변경하는 경우 update.
		// 내부에서 최초로 만들어진 object의 snapshot을 저장하고 있다가, 변경이 감지되면 update call.
		repo.save(board);
		
		BoardEntity updatedBoard = repo.findById(1L).get();
		System.out.println("Edited 1st Record => "+updatedBoard );
		
	}
	
	@Test
	@Disabled
	void pageTest() {
		// page 처리를 위해 PageRequest object를 생성해서 작업
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Page<BoardEntity> list=repo.findAll(PageRequest.of(0, 5));
		System.out.println("boardList: "+list);
		System.out.println("total record: "+list.getTotalElements());
		System.out.println("total page: "+list.getTotalPages());
		System.out.println("조회한 record: "+list.getNumberOfElements());
		System.out.println("sort: "+list.getSort());
		System.out.println("size of page: "+list.getSize());
		// pagging 처리 후 Page object에 저장된 record를 꺼내 ArrayList에 담아서 return.
		print(list.getContent());
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	@Test
//	@Disabled
	void pageTest2() {
		// page 처리를 위해 PageRequest object를 생성해서 작업
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Page<BoardEntity> list=repo.findByContentContaining("content1",PageRequest.of(0, 5));
		System.out.println("boardList: "+list);
		System.out.println("total record: "+list.getTotalElements());
		System.out.println("total page: "+list.getTotalPages());
		System.out.println("조회한 record: "+list.getNumberOfElements());
		System.out.println("sort: "+list.getSort());
		System.out.println("size of page: "+list.getSize());
		// pagging 처리 후 Page object에 저장된 record를 꺼내 ArrayList에 담아서 return.
		print(list.getContent());
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	

	
	@Test
	@Disabled
	void queryMethodTest() throws ParseException {
		System.out.println("#####################################################");
		print(repo.findByTitle("TITLE1"));
		System.out.println("#####################################################");
		print(repo.findByTitleLike("2"));
		System.out.println("#####################################################");
		print(repo.findByTitleStartingWith("1"));
		System.out.println("#####################################################");
		print(repo.findByTitle("TITLE1"));
		System.out.println("#####################################################");
		print(repo.findByCreateDateGreaterThanEqual(new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-11")));
		System.out.println("#####################################################");
		
		
	}
	
	
	void print(List<BoardEntity> list) {
		for (BoardEntity board : list) {
			System.out.println(board);
		}
	}
}
