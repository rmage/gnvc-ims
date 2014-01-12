/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.db.dvo.map;

import com.app.wms.engine.db.dvo.GrDtlView;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author ruli
 */
public class GrDtlViewMap implements ParameterizedRowMapper<GrDtlView> {

    public GrDtlView mapRow(ResultSet rs, int i) throws SQLException {
        GrDtlView dto = new GrDtlView();
        dto.setDocumentNo(rs.getString("DOCUMENT_NO"));
        dto.setItemCode(rs.getString("ITEM_CODE"));
        dto.setQuantity(rs.getBigDecimal("QUANTITY"));
        dto.setCreatedBy(rs.getBigDecimal("CREATED_BY"));
	dto.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
	dto.setUpdatedBy(rs.getBigDecimal("UPDATED_BY"));
	dto.setUpdatedDate(rs.getTimestamp("UPDATED_DATE"));
        dto.setCatalogCode(rs.getString("CATALOG_CODE"));
        return dto;
    }

}
