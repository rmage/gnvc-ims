package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.POCrossDock;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class POCrossDockMap implements ParameterizedRowMapper<POCrossDock> {

    public POCrossDock mapRow(ResultSet rs, int i) throws SQLException {
        POCrossDock dto = new POCrossDock();
        dto.setPOCode(rs.getInt(1));
        dto.setPONumber(rs.getString(2));
        dto.setWHCode(rs.getString(3));
        dto.setPODate(rs.getDate(4));
        dto.setApprovedBy(rs.getString(5));
        dto.setApprovedDate(rs.getDate(6));
        dto.setCreatedBy(rs.getString(7));
        dto.setCreatedDate(rs.getDate(8));
        dto.setUpdatedBy(rs.getString(9));
        dto.setUpdatedDate(rs.getDate(10));
        
        return dto;
    }
    
}
