/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.FGItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author Faridzi
 */
public class FGItemListMap implements ParameterizedRowMapper<FGItem> {

    public FGItem mapRow(ResultSet rs, int i) throws SQLException {
        FGItem fgi = new FGItem();
        fgi.setItemId(rs.getInt("item_id"));
        fgi.setPackId(rs.getInt("pack_id"));
        fgi.setItemCode(rs.getString("item_code"));
        fgi.setItemName(rs.getString("item_name"));
        fgi.setIsActive(rs.getString("is_active"));
        fgi.setCreatedBy(rs.getString("created_by"));
        fgi.setCreatedDate(rs.getDate("created_date"));
        fgi.setUpdatedBy(rs.getString("updated_by"));
        fgi.setUpdatedDate(rs.getDate("updated_date"));
        return fgi;
    }

}
