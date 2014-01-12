package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.WhLocation;
import com.app.wms.engine.db.dto.WhLocationDetail;

public class LocationListMap implements ParameterizedRowMapper <WhLocation>{

	@Override
	public WhLocation mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		WhLocation w = new WhLocation();
		//WhLocationDetail wd = new WhLocationDetail();
		w.setId(rs.getInt(1));
		w.setLocationId(rs.getString(2));
		w.setLocationCode(rs.getString(3));
		w.setLocationName(rs.getString(4));
		w.setProductCode(rs.getString(5));
		w.setProductName(rs.getString(6));
		//w.setWhLocationDetail(wd);u
		w.setUnitCode(rs.getString(7));
		w.setIsActive(rs.getString(8));
		
		return w;
	}

}
