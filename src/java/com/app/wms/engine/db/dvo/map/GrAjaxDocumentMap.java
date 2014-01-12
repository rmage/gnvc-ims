/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dvo.map;

import com.app.wms.engine.db.dao.spring.AbstractDAO;
import com.app.wms.engine.db.dvo.GrAjaxDocument;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author Rofy
 */
public class GrAjaxDocumentMap extends AbstractDAO implements ParameterizedRowMapper<GrAjaxDocument> {

    public GrAjaxDocument mapRow(ResultSet rs, int i) throws SQLException {
        GrAjaxDocument dto = new GrAjaxDocument();
        
        dto.setDocumentNo(rs.getString(1));
        dto.setirDocumentNo(rs.getString(2));
        dto.setItemCode(rs.getString(3));
        dto.setSNStart(rs.getString(4));
        dto.setSNEnd(rs.getString(5));
        dto.setQuantity(rs.getBigDecimal(6));
        dto.setErpDocumentNo(rs.getString(7));

        return dto;
    }
    
}
