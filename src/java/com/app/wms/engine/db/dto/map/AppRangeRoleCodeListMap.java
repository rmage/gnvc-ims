package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.ApprovalRange;
import com.app.wms.engine.db.dto.UserRole;

public class AppRangeRoleCodeListMap implements ParameterizedRowMapper <ApprovalRange>{

	@Override
	public ApprovalRange mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		ApprovalRange ar = new ApprovalRange();
		UserRole ur = new UserRole();
		ar.setUsername(rs.getString(1));
		ar.setRoleCode(rs.getString(2));
		ar.setFromAmount(rs.getBigDecimal(3));
		ar.setToAmount(rs.getBigDecimal(4));
		ur.setDepartmentCode(rs.getString(5));
		ar.setUserRole(ur);
		
		return ar;
	}

}
