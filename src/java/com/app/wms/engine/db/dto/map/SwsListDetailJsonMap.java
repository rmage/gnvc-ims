package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.StockInventory;
import com.app.wms.engine.db.dto.SwsDetail;

public class SwsListDetailJsonMap implements ParameterizedRowMapper <SwsDetail>{

	@Override
	public SwsDetail mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		SwsDetail s = new SwsDetail();
		Product p = new Product();
		StockInventory i = new StockInventory();
		
		s.setId(rs.getInt(1));
		s.setSwsnumber(rs.getString(2));
		s.setProductcode(rs.getString(3));
		p.setProductName(rs.getString(4));
		p.setProductCategory(rs.getString(5));
		p.setUom(rs.getString(6));
		s.setProduct(p);
		s.setQty(rs.getBigDecimal(7));
		s.setProducttype(rs.getString(8));
		i.setWhCode(rs.getString(9));
		i.setBalance(rs.getBigDecimal(10));
	    s.setStockInventory(i);
		
		return s;
	}

}
