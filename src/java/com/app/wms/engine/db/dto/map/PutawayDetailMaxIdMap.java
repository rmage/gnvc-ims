package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.PutawayDetail;

public class PutawayDetailMaxIdMap implements ParameterizedRowMapper <PutawayDetail>{
	
	public PutawayDetail mapRow(ResultSet rs, int row) throws SQLException {
		PutawayDetail pd = new PutawayDetail();
		
		pd.setId(rs.getInt(1));

		return pd;
	}

}
