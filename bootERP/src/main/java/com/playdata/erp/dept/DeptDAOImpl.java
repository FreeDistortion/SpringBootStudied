package com.playdata.erp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.playdata.erp.dto.DeptDTO;


//JdbcTemplate의 기능을 이용해서 access
@Repository
public class DeptDAOImpl implements DeptDAO {

	JdbcTemplate template;
	
	
	public DeptDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public DeptDAOImpl(JdbcTemplate template) {
		super();
		this.template = template;
	}

	@Override
	public List<DeptDTO> getDeptName() {
		// TODO Auto-generated method stub
		return template.query("SELECT * FROM DEPT", new DeptRowMapper());
	}

	@Override
	public int insert(DeptDTO dept) {
		String sql = "INSERT INTO DEPT VALUES(?,?,SYSDATE,NULL,?,?,?,?,?,?,?)";

		// update(SQL command, ?에 mapping될 값 나열)
		return template.update(sql, dept.getDeptno(), dept.getDeptname(), dept.getDeptlevel(), dept.getDeptstep(),
				dept.getDeptuppercode(), dept.getJob_category(), dept.getMgr_id(), dept.getDeptaddr(),
				dept.getDepttel());
	}

	@Override
	public List<DeptDTO> select() {
		//조회한 ResultSet의 record를 어떤 DTO에 mapping할지의 정보를 담고있는 RowMapper object와 SQL command를 method call 하며 전달.
		
		return template.query("SELECT * FROM DEPT", new DeptRowMapper());
	}

	@Override
	public int delete(String deptno) {
		return template.update("DELETE FROM DEPT WHERE DEPTNO=?", new Object[] {deptno});
	}

	@Override
	public DeptDTO read(String deptno) {
//		return template.queryForObject("SELECT * FROM DEPT WHERE DEPTNO="+"'"+deptno+"'", new DeptRowMapper());
		return template.queryForObject("SELECT * FROM DEPT WHERE DEPTNO=?", new Object[] {deptno}, new DeptRowMapper());
	}

	@Override
	public int update(DeptDTO dept) {
		return template.update("UPDATE DEPT SET MGR_ID=?, DEPTADDR=?, DEPTTEL=? WHERE DEPTNO=?",dept.getMgr_id(),dept.getDeptaddr(),dept.getDepttel(),dept.getDeptno());
	}

}
