package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Consolidate;

public class ConsolidateListMap implements ParameterizedRowMapper <Consolidate> {

	@Override
	public Consolidate mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		Consolidate c = new Consolidate();
		c.setConsolidateNo(rs.getString(1));
		c.setConsolidateDate(rs.getTimestamp(2));
		c.setId(rs.getInt(3));
		
		return c;
	}

}
