package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Po;
import com.app.wms.engine.db.dto.PoDetail;

public class PoDetailListMap implements ParameterizedRowMapper <PoDetail>{

	@Override
	public PoDetail mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		PoDetail pd = new PoDetail();
		Po p = new Po();
		pd.setId(rs.getInt(1));
		pd.setPonumber(rs.getString(2));
		p.setPodate(rs.getDate(3));
		p.setSupplierName(rs.getString(4));
		//pd.setProductcode(rs.getString(5));
		//pd.setQty(rs.getBigDecimal(6));
		//pd.setTermpayment(rs.getString(7));
		//pd.setTermdelivery(rs.getString(8));
		//pd.setCurrencyCode(rs.getString(9));
		//pd.setUnitprice(rs.getBigDecimal(10));
		//pd.setAmount(rs.getBigDecimal(11));
		//pd.setDiscount(rs.getBigDecimal(12));
		//pd.setPpn(rs.getBigDecimal(13));
		//pd.setPph(rs.getBigDecimal(14));
		pd.setTotal(rs.getBigDecimal(5));
		p.setRoleCode(rs.getString(6));
		p.setStatus(rs.getString(7));
		pd.setPo(p);
		
		return pd;
	}

}
