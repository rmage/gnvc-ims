package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.KittingDetail;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;

public class KittingDetailMap implements ParameterizedRowMapper <KittingDetail>{

	public KittingDetail mapRow(ResultSet rs, int row) throws SQLException {
		KittingDetail kd = new KittingDetail();
		Picking 	 pic = new Picking();
		PickingDetail pd = new PickingDetail();
		
		kd.setSo_number(rs.getString(1));
		kd.setProductId(rs.getString(2));
		kd.setProductCode(rs.getString(3));
		kd.setProductName(rs.getString(4));
		kd.setQtyPick(rs.getInt(5));
		kd.setUnitCode(rs.getString(6));
		
		return kd;
	}
}
