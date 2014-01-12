package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.SalesOrder;

public class SalesOrderDtlListMap implements ParameterizedRowMapper <SalesOrder> {

	@Override
	public SalesOrder mapRow(ResultSet rs, int row) throws SQLException {
		SalesOrder dto = new SalesOrder();
	       
        dto.setId(rs.getInt(1));
        dto.setSo_number(rs.getString(2));
        dto.setSo_date(rs.getTimestamp(3));
        dto.setCreatedBy(rs.getString(4));
        dto.setCreatedDate(rs.getTimestamp(5));
        dto.setUpdatedBy(rs.getString(6));
        dto.setUpdatedDate(rs.getTimestamp(7));
        dto.setDelivery_date(rs.getTimestamp(8));
        dto.setDelivery_name(rs.getString(9));
        dto.setDelivery_address(rs.getString(10));
        dto.setRemarks(rs.getString(11));
        
        return dto;
	}

}
