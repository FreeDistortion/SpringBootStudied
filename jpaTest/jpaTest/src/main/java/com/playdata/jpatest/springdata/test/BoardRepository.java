package com.playdata.jpatest.springdata.test;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


// 1st generic -> Entity type
// 2nd generic -> Entity's PK type
// Spring JPA 내부에서 BoardRepository object의 implementation을 만들어서 작업
// 내부에서 만둘어서 injection 후 scanning
// Spring data JPA 내부에서 지원되는 기능은 대부분 구현됨(table에 종속적인 기능은 X)
// Query method
// Spring data JPA에서 제공하는 기능은 개발자들이 모든 table에 대해 쓸 수 있는 기능 제공
// 이러한 공통 기능으로 작업할 수 없는 것들은 Query method라는 기능을 이용해 작업할 수 있도록 제공
// 1. Query create by method name
// 2. Define JPQL by using @Query
// 3. native Query command
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	List<BoardEntity> findByTitle(String title);

//	// login
//	PersonEntity findByIdAndPassword(String id, String password);
	List<BoardEntity> findByTitleLike(String title);
	List<BoardEntity> findByTitleStartingWith(String title);
	List<BoardEntity> findByTitleEndingWith(String title);
	List<BoardEntity> findByTitleContaining(String title);
	List<BoardEntity> findByBoardNoGreaterThanEqual(Long boardNo);
	List<BoardEntity> findByCreateDateGreaterThanEqual(Date createDate);
	Page<BoardEntity> findByContentContaining(String content,Pageable pageRequest);
	Slice<BoardEntity> findByContentStartingWith(String content,Pageable pageRequest);
}
