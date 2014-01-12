package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.PACrossDock;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class PACrossDockMap implements ParameterizedRowMapper<PACrossDock> {

    public PACrossDock mapRow(ResultSet rs, int i) throws SQLException {
        PACrossDock dto = new PACrossDock();
        dto.setPACode(rs.getString(1));
        dto.setGRCode(rs.getString(2));
        dto.setLocationCode(rs.getString(3));
        dto.setTallymanCode(rs.getString(4));
        dto.setWHCode(rs.getString(5));
        dto.setPAInfo(rs.getString(6));
        dto.setApprovedBy(rs.getString(7));
        dto.setApprovedDate(rs.getDate(8));
        dto.setCreatedBy(rs.getString("location_name"));
        dto.setCreatedDate(rs.getDate(10));
        dto.setUpdatedBy(rs.getString("name"));
        dto.setUpdatedDate(rs.getDate(12));
        
        return dto;
    }

}
