package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Sws;

public class SwsListMap implements ParameterizedRowMapper <Sws> {
	
	@Override
	public Sws mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		Sws s = new Sws();
		s.setId(rs.getInt(1));
		s.setSwsnumber(rs.getString(2));
		s.setSwsdate(rs.getDate(3));
		return s;
	}

}
