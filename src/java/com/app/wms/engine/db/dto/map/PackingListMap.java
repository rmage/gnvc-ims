package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Packing;

public class PackingListMap implements ParameterizedRowMapper <Packing>{

	@Override
	public Packing mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		Packing p = new Packing();
		p.setPackingNo(rs.getString(1));
		p.setPackingDate(rs.getTimestamp(2));
		p.setId(rs.getInt(3));
		
		return p;
	}

}
