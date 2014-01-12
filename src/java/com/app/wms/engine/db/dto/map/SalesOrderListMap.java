package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.SalesOrder;

public class SalesOrderListMap implements ParameterizedRowMapper <SalesOrder>{

	@Override
	public SalesOrder mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		SalesOrder so = new SalesOrder();
		so.setSo_number(rs.getString(1));
		so.setSo_date(rs.getTimestamp(2));
		so.setId(rs.getInt(3));
		
		return so;
	}

}
