package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.POCrossDockDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class POCrossDockDtlMap implements ParameterizedRowMapper<POCrossDockDtl> {

    public POCrossDockDtl mapRow(ResultSet rs, int i) throws SQLException {
        POCrossDockDtl dto = new POCrossDockDtl();
        dto.setPOCodeDtl(rs.getString(1));
        dto.setPOCode(rs.getInt(2));
        dto.setProductCode(rs.getString(3));
        dto.setPOQty(rs.getInt(4));
        dto.setPOType(rs.getString(5));
        dto.setCreatedBy(rs.getString(6));
        dto.setCreatedDate(rs.getDate(7));
        dto.setUpdatedBy(rs.getString(8));
        dto.setUpdatedDate(rs.getDate(9));
        
        return dto;
    }

}
