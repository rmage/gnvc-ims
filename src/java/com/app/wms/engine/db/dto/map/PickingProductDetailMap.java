package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PutawayDetail;
import com.app.wms.engine.db.dto.SalesOrder;
import com.app.wms.engine.db.dto.SalesOrderDetail;
import com.app.wms.engine.db.dto.WarehouseLocation;

public class PickingProductDetailMap implements ParameterizedRowMapper <PickingDetail>{
	
public PickingDetail mapRow(ResultSet rs, int row) throws SQLException {
		
		PickingDetail pic = new PickingDetail();
		PutawayDetail put = new PutawayDetail();
		Product pro = new Product();
		WarehouseLocation wh = new WarehouseLocation();
		SalesOrderDetail sod = new SalesOrderDetail();
		SalesOrder so = new SalesOrder();
		
		sod.setSo_number(rs.getString(1));
		so.setSo_date(rs.getTimestamp(2));
		pro.setProductId(rs.getString(3));
		put.setReceivedDate(rs.getTimestamp(4));
		put.setExpiredDate(rs.getTimestamp(5));
		pro.setProductCode(rs.getString(6));
		pro.setProductName(rs.getString(7));
		put.setBalance(rs.getInt(8));
		sod.setQuantitySO(rs.getInt(9));
		put.setUnitCode(rs.getString(10));
		wh.setLocationCode(rs.getString(11));	
		pic.setSoDetail(sod);
		pic.setSo(so);
		pic.setProducts(pro);
		pic.setReceivedDate(put.getReceivedDate());
		pic.setExpiredDate(put.getExpiredDate());
		pic.setBalance(put.getBalance());
		pic.setUnitCode(put.getUnitCode());
		pic.setWhlocation(wh);
		
		return pic;
	}


}
