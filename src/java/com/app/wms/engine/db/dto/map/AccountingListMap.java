package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Accounting;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.StockBalance;
import com.app.wms.engine.db.dto.StockInventory;

public class AccountingListMap implements ParameterizedRowMapper<Accounting> {

	@Override
	public Accounting mapRow(ResultSet rs, int row) throws SQLException {
		Accounting a = new Accounting();
		StockBalance sb = new StockBalance();
		StockInventory si = new StockInventory();
		Product p = new Product();
		
		sb.setDocumentDate(rs.getDate(1));
		sb.setDocumentNo(rs.getString(2));
		sb.setProductCode(rs.getString(3));
		p.setProductName(rs.getString(4));
		si.setWhCode(rs.getString(5));
		sb.setQtyIn(rs.getBigDecimal(6));
		sb.setQtyOut(rs.getBigDecimal(7));
		sb.setBalance(rs.getBigDecimal(8));
		a.setStockBalance(sb);
		a.setStockinventory(si);
		a.setProduct(p);
		a.setDocumentDate(sb.getDocumentDate());
		a.setDocumentNo(sb.getDocumentNo());
		a.setProductCode(sb.getProductCode());
		a.setProductName(p.getProductName());
		a.setWhCode(si.getWhCode());
		a.setQtyIn(sb.getQtyIn());
		a.setQtyOut(sb.getQtyOut());
		a.setBalance(sb.getBalance());
		
		return a;
	}

}
