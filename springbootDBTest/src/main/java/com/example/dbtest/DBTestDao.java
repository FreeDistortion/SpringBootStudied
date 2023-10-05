package com.example.dbtest;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBTestDao {
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	SqlSession session;
	
	public int jdbcTemplateTest() {
		return template.queryForObject("SELECT COUNT(DEPTNO) FROM DEPT", Integer.class);
	}
	
	public List<DeptDTO> select(){
		return session.selectList("com.example.dbtest.test");
	}
	
}
