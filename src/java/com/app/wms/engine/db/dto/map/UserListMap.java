package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.User;

public class UserListMap implements ParameterizedRowMapper <User>{

	@Override
	public User mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		User u = new User();
		u.setId(rs.getInt(1));
		u.setUserId(rs.getString(2));
		u.setCode(rs.getString(3));
		u.setName(rs.getString(4));
		u.setUsername(rs.getString(5));
		u.setPassword(rs.getString(6));
		u.setRoleCode(rs.getString(7));
		u.setWhCode(rs.getString(8));
		u.setCorpId(rs.getString(9));
		u.setIsActive(rs.getString(10));
		
		return u;
	}

}
