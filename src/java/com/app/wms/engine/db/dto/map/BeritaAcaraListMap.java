package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.BeritaAcara;

public class BeritaAcaraListMap implements ParameterizedRowMapper <BeritaAcara>{

	@Override
	public BeritaAcara mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		BeritaAcara b = new BeritaAcara();
		b.setId(rs.getInt(1));
		b.setBeritaNo(rs.getString(2));
		b.setBeritaDate(rs.getDate(3));
		b.setPonumber(rs.getString(4));
		
		return b;
	}

}
