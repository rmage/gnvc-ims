package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Prs;
import com.app.wms.engine.db.dto.PrsDetail;
import com.app.wms.engine.db.dto.StockInventory;

public class PRSDetailListJsonMap implements ParameterizedRowMapper <PrsDetail>{

	@Override
	public PrsDetail mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		PrsDetail pd = new PrsDetail();
		Prs prs = new Prs();
		StockInventory inv = new StockInventory();
		prs.setPrsnumber(rs.getString(1));
		prs.setPrsdate(rs.getDate(2));
		pd.setProductcode(rs.getString(3));
		pd.setProductname(rs.getString(4));
		pd.setQty(rs.getBigDecimal(5));
		pd.setUomName(rs.getString(6));
		inv.setBalance(rs.getBigDecimal(7));
		prs.setDepartmentName(rs.getString(8));
		prs.setRequestdate(rs.getDate(9));
		prs.setRemarks(rs.getString(10));
		pd.setPrs(prs);
		pd.setStockInventory(inv);
		
		return pd;
	}

}
