package com.playdata.jpatest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.playdata.jpatest.entitymanager.test.PersonDAO;
import com.playdata.jpatest.entitymanager.test.PersonEntity;

@SpringBootTest
@Transactional // 같은 transaction 처리
@Rollback(false) // rollback 안 되도록 설정
class JPATest01 {
	@Autowired
	PersonDAO dao;

	@Test
	@Disabled
	void test() {
		// check insert method of PersonDAO run right000000
		System.out.println("============================================");
		dao.insert(new PersonEntity("test1", "Kim", 50, "information of Kim"));
		System.out.println("============================================");
	}
	
	@Test
	void readTest() {
		PersonEntity person = dao.read("4");
		System.out.println(person);
	}

}
