package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.GRCrossDock;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class GRCrossDockMap implements ParameterizedRowMapper<GRCrossDock> {

    public GRCrossDock mapRow(ResultSet rs, int i) throws SQLException {
        GRCrossDock dto = new GRCrossDock();
        dto.setGRCode(rs.getString(1));
        dto.setPOCode(rs.getInt(2));
        dto.setWHCode(rs.getString(3));
        dto.setGRDate(rs.getDate(4));
        dto.setApprovedBy(rs.getString(5));
        dto.setApprovedDate(rs.getDate(6));
        dto.setCreatedBy(rs.getString(7));
        dto.setCreatedDate(rs.getDate(8));
        dto.setUpdatedBy(rs.getString("po_number"));
        dto.setUpdatedDate(rs.getDate(10));
        
        return dto;
    }

}
