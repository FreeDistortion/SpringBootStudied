package com.playdata.erp.job;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class JobRM implements RowMapper<JobDTO>{

	@Override
	public JobDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new JobDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
	}
	
}
