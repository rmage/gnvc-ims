package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Corporate;
import com.app.wms.engine.db.dto.Wh;

public class CorporateDetailMap implements ParameterizedRowMapper <Corporate>{

	@Override
	public Corporate mapRow(ResultSet rs, int row) throws SQLException {
		Corporate corp = new Corporate();
		Wh wh = new Wh();
		
		corp.setId(rs.getString(1));
		wh.setWhCode(rs.getString(2));
		corp.setWh(wh);
		
		
		return corp;
	}

}
