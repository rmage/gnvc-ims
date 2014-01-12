package com.app.wms.engine.db.dto.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import com.app.wms.engine.db.dto.PriceCatalog;

public class PriceCatalogListMap  implements ParameterizedRowMapper <PriceCatalog>{

	@Override
	public PriceCatalog mapRow(ResultSet rs, int row) throws SQLException {

		PriceCatalog p = new PriceCatalog();
		p.setId(rs.getInt(1));
		p.setCatalogCode(rs.getString(2));
		p.setCatalogName(rs.getString(3));
		p.setIsActive(rs.getString(4));
		
		return p;
	}

}
