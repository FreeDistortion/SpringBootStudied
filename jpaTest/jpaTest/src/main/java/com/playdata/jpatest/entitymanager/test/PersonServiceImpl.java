package com.playdata.jpatest.entitymanager.test;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {
	PersonDAO dao;
	@Autowired
	public PersonServiceImpl(PersonDAO dao) {
		super();
		this.dao = dao;
	}
	@Transactional
	@Override
	public void insert(PersonEntity dto) {
		dao.insert(dto);
	}
	@Override
	public PersonEntity read(String id) {
		// TODO Auto-generated method stub
		return dao.read(id);
	}
	@Transactional
	@Override
	public void delete(String id) {
		dao.delete(id);
		
	}
	@Override
	@Transactional
	public void update(PersonEntity dto) {
		dao.update(dto);
	}
	@Override
	public List<PersonEntity> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

}
