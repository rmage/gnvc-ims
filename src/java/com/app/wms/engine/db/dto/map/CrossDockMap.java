package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.CrossDock;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class CrossDockMap implements ParameterizedRowMapper<CrossDock> {

    public CrossDock mapRow(ResultSet rs, int i) throws SQLException {
        CrossDock dto = new CrossDock();
        dto.setCDCode(rs.getString(1));
        dto.setPACode(rs.getString(2));
        dto.setPCode(rs.getString(3));
        dto.setWHCode(rs.getString(4));
        dto.setCDDateIn(rs.getDate(5));
        dto.setCDDateOut(rs.getDate(6));
        dto.setCreatedBy(rs.getString(7));
        dto.setCreatedDate(rs.getDate(8));
        dto.setUpdatedBy(rs.getString(9));
        dto.setUpdatedDate(rs.getDate(10));
        
        return dto;
    }
    
}