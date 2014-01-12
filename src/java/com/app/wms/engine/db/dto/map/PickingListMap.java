package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Picking;

public class PickingListMap implements ParameterizedRowMapper <Picking>{

	@Override
	public Picking mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		Picking pi = new Picking();
		pi.setPickingNo(rs.getString(1));
		pi.setPickingDate(rs.getTimestamp(2));
		pi.setStatusApp(rs.getString(3));
		pi.setId(rs.getInt(4));
		
		return pi;
	}

}
