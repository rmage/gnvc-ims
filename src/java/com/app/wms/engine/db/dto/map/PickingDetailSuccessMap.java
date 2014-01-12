package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Picking;
import com.app.wms.engine.db.dto.PickingDetail;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.WarehouseLocation;
import com.app.wms.engine.db.dto.WhLocation;

public class PickingDetailSuccessMap implements ParameterizedRowMapper <Picking>{
	
	public Picking mapRow(ResultSet rs, int row) throws SQLException {
			
		Picking p = new Picking();
		PickingDetail pd = new PickingDetail();
		Product pro = new Product();
		WarehouseLocation wl = new WarehouseLocation();
	    pd.setPickingId(rs.getString(1));
		pro.setProductId(rs.getString(2));
		pro.setProductCode(rs.getString(3));
		pro.setProductName(rs.getString(4));
		pd.setBalance(rs.getInt(5));
		pd.setUnitCode(rs.getString(6));
		wl.setLocationCode(rs.getString(7));
		
		pd.setWhlocation(wl);
		pd.setProducts(pro);
		p.setPickingDetail(pd);
		
			
		return p;
	}


}
