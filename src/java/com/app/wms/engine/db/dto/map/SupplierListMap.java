package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Supplier;

public class SupplierListMap implements ParameterizedRowMapper <Supplier> {
	
	@Override
	public Supplier mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		Supplier s = new Supplier();
		s.setId(rs.getInt(1));
		s.setSupplierCode(rs.getString(2));
		s.setSupplierName(rs.getString(3));
		s.setIsActive(rs.getString(4));
		
		return s;
	}

}
