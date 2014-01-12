package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.CanvassingDetail;
import com.app.wms.engine.db.dto.Prs;

public class PrsListMap implements ParameterizedRowMapper <Prs>{

	public Prs mapRow(ResultSet rs, int row) throws SQLException {
		Prs p = new Prs();
		CanvassingDetail cd = new CanvassingDetail();
		p.setId(rs.getInt(1));
		p.setPrsnumber(rs.getString(2));
		p.setPrsdate(rs.getDate(3));
		p.setRequestdate(rs.getDate(4));
		p.setDeliverydate(rs.getDate(5));
		p.setPoreferensi(rs.getString(6));
		p.setRemarks(rs.getString(7));
		p.setCreatedby(rs.getString(8));
		p.setDepartmentName(rs.getString(9));
		cd.setSupplierCode(rs.getString(10));
		cd.setIsSelected(rs.getString(11));
		p.setCanvassingDetail(cd);
		
		return p;
	}
}
