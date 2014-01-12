package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.PutawayDetail;

public class PutawayDetailProductMap implements ParameterizedRowMapper <PutawayDetail>{

	public PutawayDetail mapRow(ResultSet rs, int row) throws SQLException {
		PutawayDetail pd = new PutawayDetail();
		Product pro = new Product();
		
		pd.setProductCode(rs.getString(1));
		pro.setProductName(rs.getString(2));
		pd.setProduct(pro);
		pd.setUnitCode(rs.getString(3));
		pd.setLocationCode(rs.getString(4));
		pd.setBalance(rs.getInt(5));

		return pd;
	}

}
