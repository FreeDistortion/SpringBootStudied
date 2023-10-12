package com.playdata.jpatest.entitymanager.test;
import java.util.List;

public interface PersonDAO {
	void insert(PersonEntity dto) ;
	PersonEntity read(String id);
	void delete(String id);
	void update(PersonEntity dto);
	List<PersonEntity> list();
}
