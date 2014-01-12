package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Kitting;

public class KittingListMap implements ParameterizedRowMapper <Kitting> {

	@Override
	public Kitting mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		Kitting k = new Kitting();
		k.setKittingNo(rs.getString(1));
		k.setKittingDate(rs.getTimestamp(2));
		k.setId(rs.getInt(3));
		return k;
	}

}
