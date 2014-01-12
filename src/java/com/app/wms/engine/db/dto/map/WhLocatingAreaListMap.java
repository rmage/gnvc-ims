package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.WhLocatingArea;

public class WhLocatingAreaListMap implements ParameterizedRowMapper <WhLocatingArea>{

	@Override
	public WhLocatingArea mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		WhLocatingArea w = new WhLocatingArea();
		w.setId(rs.getInt(1));
		w.setLocatingId(rs.getString(2));
		w.setLocatingArea(rs.getString(3));
		w.setLocatingCondition(rs.getString(4));
		
		return w;
	}

}
