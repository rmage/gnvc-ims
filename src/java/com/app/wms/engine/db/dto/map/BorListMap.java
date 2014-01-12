package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Bor;

public class BorListMap implements ParameterizedRowMapper <Bor>{
	
	@Override
	public Bor mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		Bor b = new Bor();
		b.setId(rs.getInt(1));
		b.setBornumber(rs.getString(2));
		b.setBordate(rs.getDate(3));
		return b;
	}


}
