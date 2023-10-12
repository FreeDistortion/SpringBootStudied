package com.playdata.jpatest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.playdata.jpatest.relationship.DeptEntity;
import com.playdata.jpatest.relationship.Emp;

@SpringBootTest
@Transactional
@Rollback(false)
class JPARelationTest {

	// EntityManager 의 경우 @Autowired 대신 쓸 수 있음
	@PersistenceContext
	EntityManager em;

	@Test
	@Disabled
	void test() {
		DeptEntity dept1 = new DeptEntity("전산", "Kim");
		DeptEntity dept2 = new DeptEntity("인사", "Lee");
		DeptEntity dept3 = new DeptEntity("기획", "Park");

		em.persist(dept1);
		em.persist(dept2);
		em.persist(dept3);

		Emp emp1 = new Emp("AAA", "Seoul", dept1);
		Emp emp2 = new Emp("BBB", "New York", dept1);
		Emp emp3 = new Emp("CCC", "Tokyo", dept2);
		Emp emp4 = new Emp("DDD", "LA", dept2);
		Emp emp5 = new Emp("EEE", "Texax", dept3);

		em.persist(emp1);
		em.persist(emp2);
		em.persist(emp3);
		em.persist(emp4);
		em.persist(emp5);

		// 초기화
		em.flush();
		em.clear();

		// 모든 emp data checkr
		List<Emp> empList = em.createQuery("SELECT E FROM Emp E", Emp.class).getResultList();
		
		for (Emp emp : empList) {
			System.out.println(emp.toString());
		}
	}

	@Test
	void test2() {
		DeptEntity dept1 = new DeptEntity("전산", "Kim");
		DeptEntity dept2 = new DeptEntity("인사", "Lee");
		DeptEntity dept3 = new DeptEntity("기획", "Park");

		em.persist(dept1);
		em.persist(dept2);
		em.persist(dept3);

		Emp emp1 = new Emp("AAA", "Seoul", dept1);
		Emp emp2 = new Emp("BBB", "New York", dept1);
		Emp emp3 = new Emp("CCC", "Tokyo", dept2);
		Emp emp4 = new Emp("DDD", "LA", dept2);
		Emp emp5 = new Emp("EEE", "Texax", dept3);

		em.persist(emp1);
		em.persist(emp2);
		em.persist(emp3);
		em.persist(emp4);
		em.persist(emp5);

		// 초기화
		em.flush();
		em.clear();

		// 모든 emp data checkr
		List<Emp> empList = em.createQuery("SELECT E FROM Emp E", Emp.class).getResultList();
		
		for (Emp emp : empList) {
			System.out.println(emp.toString());
		}
	}
}
