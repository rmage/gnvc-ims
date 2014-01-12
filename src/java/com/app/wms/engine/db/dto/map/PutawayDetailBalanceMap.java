package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.WhLocation;

public class PutawayDetailBalanceMap implements ParameterizedRowMapper <PutawayDetail>{

	public PutawayDetail mapRow(ResultSet rs, int row) throws SQLException {
		PutawayDetail put = new PutawayDetail();
		Product pro = new Product();
		
		put.setLocationCode(rs.getString(1));
		put.setProductCode(rs.getString(2));
		pro.setProductName(rs.getString(3));
		put.setProduct(pro);
		put.setBalance(rs.getInt(4));
		put.setId(rs.getInt(5));
		
		return put;
	}


}
