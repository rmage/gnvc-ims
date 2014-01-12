package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Pts;

public class PtslistMap implements ParameterizedRowMapper <Pts>{

	@Override
	public Pts mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		Pts p = new Pts();
		p.setId(rs.getInt(1));
        p.setPtsCode(rs.getString(2));
        p.setPtsDate(rs.getString(3));
		
		return p;
	}

}
