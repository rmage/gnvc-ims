package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.PCrossDock;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class PCrossDockMap implements ParameterizedRowMapper<PCrossDock> {

    public PCrossDock mapRow(ResultSet rs, int i) throws SQLException {
        PCrossDock dto = new PCrossDock();
        dto.setPCode(rs.getString(1));
        dto.setSOCode(rs.getString(2));
        dto.setTallymanCode(rs.getString(3));
        dto.setWHCode(rs.getString(4));
        dto.setPDate(rs.getDate(5));
        dto.setApprovedBy(rs.getString(6));
        dto.setApprovedDate(rs.getDate(7));
        dto.setCreatedBy(rs.getString(8));
        dto.setCreatedDate(rs.getDate(9));
        dto.setUpdatedBy(rs.getString("name"));
        dto.setUpdatedDate(rs.getDate(11));
        
        return dto;
    }

}
