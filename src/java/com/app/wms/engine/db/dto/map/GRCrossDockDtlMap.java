package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.GRCrossDockDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class GRCrossDockDtlMap implements ParameterizedRowMapper<GRCrossDockDtl> {

    public GRCrossDockDtl mapRow(ResultSet rs, int i) throws SQLException {
        GRCrossDockDtl dto = new GRCrossDockDtl();
        dto.setGRCodeDtl(rs.getString(1));
        dto.setGRCode(rs.getString(2));
        dto.setPOCodeDtl(rs.getString(3));
        dto.setGRStatus(rs.getString(4));
        dto.setCreatedBy(rs.getString(5));
        dto.setCreatedDate(rs.getDate(6));
        dto.setUpdatedBy(rs.getString(7));
        dto.setUpdatedDate(rs.getDate(8));
        
        return dto;
    }

}
