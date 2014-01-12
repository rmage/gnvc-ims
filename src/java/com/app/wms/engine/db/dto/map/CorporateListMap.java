package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Corp;

public class CorporateListMap implements ParameterizedRowMapper <Corp> {

	@Override
	public Corp mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		Corp c = new Corp();
		c.setId(rs.getInt(1));
		c.setCorpId(rs.getString(2));
		c.setCorpName(rs.getString(3));
		c.setIsActive(rs.getString(4));
		
		return c;
	}

}
