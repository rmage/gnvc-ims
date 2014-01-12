package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.SalesOrder;

public class SalesOrderPickingListMap implements ParameterizedRowMapper <Picking>{

	@Override
	public Picking mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		Picking dto = new Picking();
		SalesOrder so = new SalesOrder();
		//dto.setId( rs.getInt( 1 ) );
		dto.setPickingNo( rs.getString( 1 ) );
		dto.setPickingDate( rs.getTimestamp(2 ) );
		dto.setSoNumber( rs.getString( 3 ) );
		dto.setSoDate( rs.getTimestamp(4 ) );
		so.setRemarks(rs.getString(5));
		dto.setSalesOrder(so);
		
		return dto;
	}

}
