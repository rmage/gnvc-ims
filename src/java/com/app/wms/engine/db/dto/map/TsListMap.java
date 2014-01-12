package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Ts;

public class TsListMap implements ParameterizedRowMapper <Ts>{

	@Override
	public Ts mapRow(ResultSet rs, int row) throws SQLException {
		Ts t = new Ts();
		t.setId(rs.getInt(1));
		t.setTsnumber(rs.getString(2));
		t.setTsdate(rs.getDate(3));
		return t;
	}

}
