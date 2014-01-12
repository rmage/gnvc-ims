package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.Quarantine;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 11 February 2013
 */

public class QuarantineMap implements ParameterizedRowMapper<Quarantine> {

    public Quarantine mapRow(ResultSet rs, int i) throws SQLException {
        Quarantine dto = new Quarantine();
        dto.setQCode(rs.getString(1));
        dto.setPOCode(rs.getInt(2));
        dto.setPutawayCode(rs.getString(3));
        dto.setWhCode(rs.getString(4));
        dto.setQDate(rs.getDate(5));
        dto.setCreatedBy(rs.getString("po_number"));
        dto.setCreatedDate(rs.getDate(7));
        dto.setUpdatedBy(rs.getString(8));
        dto.setUpdatedDate(rs.getDate(9));
        
        return dto;
    }

}
