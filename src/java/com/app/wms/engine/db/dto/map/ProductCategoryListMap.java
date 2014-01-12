package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.ProductCategory;

public class ProductCategoryListMap implements ParameterizedRowMapper <ProductCategory>{

	@Override
	public ProductCategory mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		ProductCategory p = new ProductCategory();
		p.setId(rs.getInt(1));
		p.setCategoryCode(rs.getString(2));
		p.setCategoryName(rs.getString(3));
		p.setIsActive(rs.getString(4));
		
		return p;
	}

}
