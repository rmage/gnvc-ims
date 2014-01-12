package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.Vgrdetailproductcategory;

public class VgrdetailproductcategoryListMap implements ParameterizedRowMapper <Vgrdetailproductcategory>{

	@Override
	public Vgrdetailproductcategory mapRow(ResultSet rs, int row) throws SQLException {

		Vgrdetailproductcategory vgr = new Vgrdetailproductcategory();
		
		vgr.setId(rs.getInt(1));
		vgr.setGrnumber(rs.getString(2));
		vgr.setProductcode(rs.getString(3));
		vgr.setQtyreal(rs.getInt(4));
		vgr.setStatus(rs.getString(5));
		vgr.setExpirydate(rs.getDate(6));
		vgr.setRemark(rs.getString(7));
		vgr.setLotid(rs.getInt(8));
		vgr.setProductName(rs.getString(9));
		vgr.setCategoryName(rs.getString(10));
		vgr.setQtygood(rs.getInt(11));
		vgr.setQtydmg(rs.getInt(12));
		vgr.setProducttype(rs.getString(13));

		return vgr;
		
	}

}
