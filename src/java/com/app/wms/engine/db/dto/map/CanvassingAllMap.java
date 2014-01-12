package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Canvassing;
import com.app.wms.engine.db.dto.CanvassingDetail;

public class CanvassingAllMap implements ParameterizedRowMapper <CanvassingDetail>{

	@Override
	public CanvassingDetail mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		CanvassingDetail cd = new CanvassingDetail();
		
		cd.setId( rs.getInt( 1 ) );
		cd.setSupplierCode( rs.getString( 2 ) );
		cd.setPrsnumber( rs.getString( 3 ) );
		cd.setProductcode( rs.getString( 4 ) );
		cd.setProductname( rs.getString( 5 ) );
		cd.setPriceunit( rs.getBigDecimal( 6 ) );
		cd.setIsSelected( rs.getString( 7 ) );
		cd.setCanvassingdate(rs.getDate(8));
		
		return cd;
	}

}
