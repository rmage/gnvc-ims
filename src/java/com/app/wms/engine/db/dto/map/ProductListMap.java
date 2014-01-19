package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Product;

public class ProductListMap implements ParameterizedRowMapper <Product>{

	@Override
	public Product mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		Product p = new Product();
		p.setId(rs.getInt(1));
		p.setProductId(rs.getString(2));
		p.setProductCode(rs.getString(3));
		p.setProductName(rs.getString(4));
		p.setIsActive(rs.getString(5));
                p.setProductCategory(rs.getString("product_category"));
		
		return p;
	}

}
