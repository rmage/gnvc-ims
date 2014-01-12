package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.WhLocationDetail;

public class LocationProductDetailMap implements ParameterizedRowMapper <WarehouseLocation>{

	@Override
	public WarehouseLocation mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		WarehouseLocation wl = new WarehouseLocation();
		WhLocationDetail wd = new WhLocationDetail();
		Product pro = new Product();
		
		wd.setProductCode(rs.getString(1));
		wd.setProductName(rs.getString(2));
		wd.setProductCategory(rs.getString(3));
		pro.setBrandName(rs.getString(4));
		pro.setProductDescription(rs.getString(5));
		wl.setWhLocationDetail(wd);
		wl.setProduct(pro);
		
		return wl;
	}

}
