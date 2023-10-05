package com.playdata.erp.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

	JobDAO dao;
	
	
	@Autowired
	public JobServiceImpl(JobDAO dao) {
		super();
		this.dao = dao;
	}

	public JobServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(JobDTO job) {
		// TODO Auto-generated method stub
		return dao.insert(job);
	}

	@Override
	public List<JobDTO> select() {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public int delete(String job_id) {
		// TODO Auto-generated method stub
		return dao.delete(job_id);
	}

	@Override
	public JobDTO read(String job_id) {
		// TODO Auto-generated method stub
return dao.read(job_id);
	}

	@Override
	public int update(JobDTO job) {
		// TODO Auto-generated method stub
		return dao.update(job);
	}

}
