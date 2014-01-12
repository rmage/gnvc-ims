package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.DOCrossDock;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class DOCrossDockMap implements ParameterizedRowMapper<DOCrossDock> {

    public DOCrossDock mapRow(ResultSet rs, int i) throws SQLException {
        DOCrossDock dto = new DOCrossDock();
        dto.setDOCode(rs.getString(1));
        dto.setSOCode(rs.getString(2));
        dto.setWHCode(rs.getString(3));
        dto.setDOName(rs.getString(4));
        dto.setDODate(rs.getDate(5));
        dto.setApprovedBy(rs.getString(6));
        dto.setApprovedDate(rs.getDate(7));
        dto.setCreatedBy(rs.getString(8));
        dto.setCreatedDate(rs.getDate(9));
        dto.setUpdatedBy(rs.getString(10));
        dto.setUpdatedDate(rs.getDate(11));
        
        return dto;
    }

}
