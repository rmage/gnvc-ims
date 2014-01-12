package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.SOCrossDock;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class SOCrossDockMap implements ParameterizedRowMapper<SOCrossDock> {

    public SOCrossDock mapRow(ResultSet rs, int i) throws SQLException {
        SOCrossDock dto = new SOCrossDock();
        dto.setSOCode(rs.getString(1));
        dto.setPOCode(rs.getString(2));
        dto.setConsigneeCode(rs.getString(3));
        dto.setWHCode(rs.getString(4));
        dto.setSODate(rs.getDate(5));
        dto.setApprovedBy(rs.getString(6));
        dto.setApprovedDate(rs.getDate(7));
        dto.setCreatedBy(rs.getString("po_number"));
        dto.setCreatedDate(rs.getDate(9));
        dto.setUpdatedBy(rs.getString("consignee_name"));
        dto.setUpdatedDate(rs.getDate(11));
        
        return dto;
    }

}
