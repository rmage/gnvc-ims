package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Eds;

public class EdsListMap implements ParameterizedRowMapper<Eds>{

	@Override
	public Eds mapRow(ResultSet rs, int row) throws SQLException {
		Eds t = new Eds();
		t.setId(rs.getInt(1));
		t.setEdsnumber(rs.getString(2));
		t.setEdsdate(rs.getDate(3));
		return t;
	}

}
