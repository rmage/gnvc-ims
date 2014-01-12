package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Delivery;

public class DeliveryOrderListMap implements ParameterizedRowMapper <Delivery>{

	@Override
	public Delivery mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		Delivery d = new Delivery();
		d.setDeliveryNo(rs.getString(1));
		d.setDeliveryDate(rs.getTimestamp(2));
		d.setTransporterType(rs.getString(3));
		d.setId(rs.getInt(4));
		
		return d;
	}

}
