package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Department;

public class DepartmentListMap implements ParameterizedRowMapper <Department>{
	
	@Override
	public Department mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		Department d = new Department();
		d.setId(rs.getInt(1));
		d.setDepartmentCode(rs.getString(2));
		d.setDepartmentName(rs.getString(3));
		d.setIsActive(rs.getString(4));
		
		return d;
	}

}
