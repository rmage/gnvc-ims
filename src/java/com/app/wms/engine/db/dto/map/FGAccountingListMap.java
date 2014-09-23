/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.FGStockCardAccounting;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author Faridzi
 */
public class FGAccountingListMap implements ParameterizedRowMapper<FGStockCardAccounting> {

    public FGStockCardAccounting mapRow(ResultSet rs, int i) throws SQLException {
        FGStockCardAccounting fgStockCardAccounting = new FGStockCardAccounting();
        fgStockCardAccounting.setItemId(rs.getInt("item_id"));
        fgStockCardAccounting.setItemName(rs.getString("item_name"));
        fgStockCardAccounting.setEndQuantity(rs.getDouble("sc_ending"));
        fgStockCardAccounting.setScDate(rs.getDate("sc_date"));

        return fgStockCardAccounting;
    }

}
