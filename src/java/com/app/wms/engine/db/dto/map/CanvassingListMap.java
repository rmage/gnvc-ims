package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.CanvassingDetail;
import com.app.wms.engine.db.dto.PrsDetail;

public class CanvassingListMap implements ParameterizedRowMapper <CanvassingDetail>{

	@Override
	public CanvassingDetail mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		CanvassingDetail c = new CanvassingDetail();
		PrsDetail p = new PrsDetail();
		
		c.setId(rs.getInt(1));
		c.setSupplierCode(rs.getString(2));
		c.setPrsnumber(rs.getString(3));
		c.setProductcode(rs.getString(4));
		c.setProductname(rs.getString(5));
		p.setUomName(rs.getString(6));
		p.setQty(rs.getBigDecimal(7));
		c.setPriceunit(rs.getBigDecimal(8));
		c.setWarranty(rs.getDate(9));
		c.setTermpayment(rs.getString(10));
		c.setTermdelivery(rs.getString(11));
		c.setDiscount(rs.getBigDecimal(12));
		c.setPph(rs.getBigDecimal(13));
		c.setPpn(rs.getBigDecimal(14));
		c.setIsSelected(rs.getString(15));
		c.setPrsDetail(p);
		
		return c;
	}

}
