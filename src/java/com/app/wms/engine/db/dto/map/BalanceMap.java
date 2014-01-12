package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.StockInventory;

public class BalanceMap implements ParameterizedRowMapper <StockInventory>{

	@Override
	public StockInventory mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		StockInventory s = new StockInventory();
    	s.setBalance(rs.getBigDecimal(1));
		return s;
	}

}
