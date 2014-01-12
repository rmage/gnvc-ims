package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Consignee;

public class ConsigneeListMap implements ParameterizedRowMapper <Consignee> {

	@Override
	public Consignee mapRow(ResultSet rs, int row) throws SQLException {
		// TODO Auto-generated method stub
		
		Consignee c = new Consignee();
		c.setId(rs.getInt(1));
		c.setConsigneeCode(rs.getString(2));
		c.setConsigneeName(rs.getString(3));
		c.setConsigneePIC(rs.getString(4));
		c.setAddress1(rs.getString(5));
		c.setAddress2(rs.getString(6));
		c.setAddress3(rs.getString(7));
		c.setConsigneePhone(rs.getString(8));
		c.setIsActive(rs.getString(9));
		
		return c;
	}

}
