package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Bundle;

public class BundleListMap implements ParameterizedRowMapper <Bundle>{

	@Override
	public Bundle mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		Bundle b = new Bundle();
		b.setBundleCode(rs.getString(2));
		b.setBundleName(rs.getString(3));
		return b;
	}

}
