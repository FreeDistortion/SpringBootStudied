package com.playdata.erp.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JobDAOImpl implements JobDAO {

	JdbcTemplate templ;

	@Autowired
	public JobDAOImpl(JdbcTemplate templ) {
		super();
		this.templ = templ;
	}

	public JobDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(JobDTO job) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO JOB VALUES(");
		sb.append("?,?,?,?");
		sb.append(")");

		return templ.update(sb.toString(), job.getJob_id(), job.getJob_name(), job.getJob_category(),
				job.getMenupath());
	}

	@Override
	public List<JobDTO> select() {
		// TODO Auto-generated method stub
		return templ.query("SELECT * FROM JOB", new JobRM());
	}

	@Override
	public int delete(String job_id) {
		// TODO Auto-generated method stub
		return templ.update("DELETE FROM JOB WHERE JOB_ID=?", new Object[] { job_id });
	}

	@Override
	public JobDTO read(String job_id) {
		// TODO Auto-generated method stub
		return templ.queryForObject("SELECT * FROM JOB WHERE JOB_ID=?", new Object[] { job_id }, new JobRM());
	}

	@Override
	public int update(JobDTO job) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE JOB SET ");
		sb.append("JOB_NAME=?, JOB_CATEGORY=?, MENUPATH=?");
		return templ.update(sb.toString(),job.getJob_name(),job.getJob_category(),job.getMenupath());
	}

}
