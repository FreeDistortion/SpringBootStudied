package com.playdata.erp.job;

import java.util.List;


public interface JobDAO {
	int insert(JobDTO job);
	public List<JobDTO> select();
	public int delete(String job_id);
	public JobDTO read(String job_id);
	int update(JobDTO job);
}
