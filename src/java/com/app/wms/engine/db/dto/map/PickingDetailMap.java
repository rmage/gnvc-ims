package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.WarehouseLocation;


public class PickingDetailMap implements ParameterizedRowMapper <PickingDetail>{

public PickingDetail mapRow(ResultSet rs, int row) throws SQLException {
		
		PickingDetail pic = new PickingDetail();
		PutawayDetail put = new PutawayDetail();
		Product pro = new Product();
		WarehouseLocation wh = new WarehouseLocation();
		
		pro.setProductId(rs.getString(1));
		pro.setProductCode(rs.getString(2));
		pro.setProductName(rs.getString(3));
		put.setBalance(rs.getInt(4));
		put.setUnitCode(rs.getString(5));
		wh.setLocationCode(rs.getString(6));	
		pic.setProducts(pro);
		pic.setBalance(put.getBalance());
		pic.setUnitCode(put.getUnitCode());
		pic.setWhlocation(wh);
			
		/* default pickingDetail
		dto.setPickingId(rs.getString(1));
		dto.getProducts().setProductId(rs.getString(2));
		dto.getProducts().setProductCode(rs.getString(3));
		dto.getProducts().setProductName(rs.getString(4));
		dto.setExpiredDate(rs.getDate(5));
		dto.setUnitCode(rs.getString(6));
		dto.setQtyOrder(rs.getInt(7));
		dto.setQtyPick(rs.getInt(8));
		dto.setBalance(rs.getInt(9));
		dto.setUserId(rs.getString(10));
		dto.getCorp().setId(rs.getString(11));
		dto.getWhlocation().setLocationCode(rs.getString(12));
		dto.getWh().setWhCode(rs.getString(13));
		*/
		return pic;
	}

}
