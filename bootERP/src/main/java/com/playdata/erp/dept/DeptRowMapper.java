package com.playdata.erp.dept;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.playdata.erp.dto.DeptDTO;

//record 한 개가 method 한 번 호출 - mapROw
//while or if command 안에서 object와 mapping하는 code 구현
//query, queryForObject method 내부에서 DB에서 조회한 record를 어떤 object로 mapping해야 하는지 정보를 담고있는 object.
public class DeptRowMapper implements RowMapper<DeptDTO> {

	@Override
	public DeptDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeptDTO dept = new DeptDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
		return dept;
	}
	
	
}
