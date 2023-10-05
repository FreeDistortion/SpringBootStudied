package com.playdata.erp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playdata.erp.dto.DeptDTO;

// call DeptDAO method
// -> business logic
// -> transaction 처리
// -> controller에서 받은 data를 가공해서 DAO로 넘기거나, DAO에서 받은 data를 가공해서 controller에 넘기기
@Service
public class DeptServiceImpl implements DeptService {
	DeptDAO dao;
	
	@Autowired
	public DeptServiceImpl(DeptDAO dao) {
		super();
		this.dao = dao;
	}

	public DeptServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DeptDTO> getDeptName() {
		// TODO Auto-generated method stub
		return dao.getDeptName();
	}

	@Override
	public int insert(DeptDTO dept) {
		// TODO Auto-generated method stub
		return dao.insert(dept);
	}

	@Override
	public List<DeptDTO> select() {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public int delete(String deptno) {
		// TODO Auto-generated method stub
		return dao.delete(deptno);
	}

	@Override
	public DeptDTO read(String deptno) {
		// TODO Auto-generated method stub
		return dao.read(deptno);
	}

	@Override
	public int update(DeptDTO dept) {
		// TODO Auto-generated method stub
		return dao.update(dept);
	}

}
