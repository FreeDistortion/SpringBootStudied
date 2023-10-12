package com.playdata.jpatest.entitymanager.test;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAOImpl implements PersonDAO {

	// JPA는 EntityManager를 이용해 작업을 처리(spring data JPA 내부에서도 사용).
	EntityManager em;

	@Autowired
	public PersonDAOImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public void insert(PersonEntity dto) {
		// TODO Auto-generated method stub
		System.out.println("B4 call persist method");
		em.persist(dto);
//		em.persist(dto);
//		em.persist(dto);
//		em.persist(new PersonEntity("1234", "Kim", 99, "information of Kim"));
		System.out.println("AFTER call persist method");
		System.out.println("====================================================");

		// DB에 저장된 record를 조회하는 작업
		// Cache에 저장된 object가 있으면 DB에서 조회하지 않고 cache에서 바로 object를 꺼내 옴.
		// SQL을 모두 실행하라는 뜻(cache 비우지 않음)
		em.flush();
		// cache clear.
		em.clear();
		PersonEntity pe = em.find(PersonEntity.class, dto.getId());
		System.out.println(pe);
		System.out.println(pe == dto);
	}

	@Override
	public PersonEntity read(String id) {
		// TODO Auto-generated method stub
		return em.find(PersonEntity.class, Long.parseLong(id));
	}

	@Override
	public void delete(String id) {
		// 조회 후 삭제
		PersonEntity person = em.find(PersonEntity.class, Long.parseLong(id));
		em.remove(person);
	}

	@Override
	public void update(PersonEntity dto) {
		// 조회 후 수정 - call setter method
		PersonEntity person = em.find(PersonEntity.class, dto);
		person.setPassword(dto.getPassword());
		person.setJumsu(dto.getJumsu());

	}

	// JPQL을 사용해 조회 - OOP query
	// JPQL을 사용하면 이미 세팅되어 있는 DBMS에 맞춰 query가 만들어짐.
	// 복잡한 검색 조건 또는 join이 들어가는 경우, ANSI join에서 사용하는 모든 join을 적용할 수 있다.
	// SELECT, FROM WHERE, GROUP BY, HAVING, JOIN 지원 가능
	@Override
	public List<PersonEntity> list() {
		List<PersonEntity> list=em.createQuery("SELECT p FROM PersonEntity as p", PersonEntity.class)
		.getResultList(); 
		return list;
	}

}
