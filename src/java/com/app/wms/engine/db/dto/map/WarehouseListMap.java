package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Wh;

public class WarehouseListMap implements ParameterizedRowMapper <Wh>{

	@Override
	public Wh mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		Wh w = new Wh();
		w.setId(rs.getInt(1));
		w.setWhCode(rs.getString(2));
		w.setCode(rs.getString(3));
		w.setName(rs.getString(4));
		w.setRegion(rs.getString(5));
		w.setCorpId(rs.getString(6));
		w.setIsActive(rs.getString(7));
		
		return w;
	}

}
