package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.QuarantineDtl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 11 February 2013
 */

public class QuarantineDtlMap implements ParameterizedRowMapper<QuarantineDtl> {

    public QuarantineDtl mapRow(ResultSet rs, int i) throws SQLException {
        QuarantineDtl dto = new QuarantineDtl();
        dto.setQCodeDtl(rs.getString(1));
        dto.setQCode(rs.getString(2));
        dto.setProductCode(rs.getString(3));
        dto.setCreatedBy(rs.getString(4));
        dto.setCreatedDate(rs.getDate(5));
        dto.setUpdatedBy(rs.getString(6));
        dto.setUpdatedDate(rs.getDate(7));
        
        return dto;
    }

}
