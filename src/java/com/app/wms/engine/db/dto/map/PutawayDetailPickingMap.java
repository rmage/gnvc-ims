package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.WhLocation;

public class PutawayDetailPickingMap implements ParameterizedRowMapper <PutawayDetail> {

	public PutawayDetail mapRow(ResultSet rs, int row) throws SQLException {
		PutawayDetail put = new PutawayDetail();
		WhLocation loc = new WhLocation();
		
		put.setId(rs.getInt(1));
		put.setBalance(rs.getInt(2));
		put.setLocationCode(rs.getString(3));
		loc.setLocationName(rs.getString(4));
		put.setWhLocation(loc);
		put.setProductCode(rs.getString(5));
		//put.setLotid(rs.getString(5));
		//put.setReceivedDate(rs.getTimestamp(6));
		//put.setExpiredDate(rs.getTimestamp(7));
		//put.setQtyPut(rs.getInt(5));
		
		return put;
	}
}
