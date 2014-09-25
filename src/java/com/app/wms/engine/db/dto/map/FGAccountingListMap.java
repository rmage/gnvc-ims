/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.FGStockCardAccounting;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
        fgStockCardAccounting.setVarCost(rs.getBigDecimal("amount_var_cost"));
        fgStockCardAccounting.setFixCost(rs.getBigDecimal("amount_fix_cost"));
        fgStockCardAccounting.setTotalCost(rs.getBigDecimal("amount_total"));
        fgStockCardAccounting.setCurrencyCodeFrom(rs.getString("currency_code_from"));
        fgStockCardAccounting.setRateDate(rs.getDate("rate_date"));
        fgStockCardAccounting.setRateValue(rs.getBigDecimal("rate_value"));

        /*CONVERT FROM DEFAULT (USD) TO IDR AND THEN MULTIPLY WITH QUANTITY*/
        BigDecimal tempVar = BigDecimal.ZERO;
        BigDecimal tempFix = BigDecimal.ZERO;
        BigDecimal tempTotal = BigDecimal.ZERO;
        BigDecimal rateVal = BigDecimal.ZERO;
        BigDecimal qty = BigDecimal.ZERO;
        
        rateVal = fgStockCardAccounting.getRateValue();
        qty = BigDecimal.valueOf(fgStockCardAccounting.getEndQuantity());

        /*CURRENCY CONVERTER*/
        if (rs.getString("currency_code_from").equalsIgnoreCase("USD")) {
            tempVar = fgStockCardAccounting.getVarCost().multiply(rateVal);
            tempFix = fgStockCardAccounting.getFixCost().multiply(rateVal);
            tempTotal = fgStockCardAccounting.getTotalCost().multiply(rateVal);
        } else {
            if (rateVal.compareTo(BigDecimal.ZERO) > 0) {
                tempVar = fgStockCardAccounting.getVarCost().divide(rateVal, 4, RoundingMode.HALF_UP);
                tempFix = fgStockCardAccounting.getFixCost().divide(rateVal, 4, RoundingMode.HALF_UP);
                tempTotal = fgStockCardAccounting.getTotalCost().divide(rateVal, 4, RoundingMode.HALF_UP);
            }
        }

        /*TOTAL WITH QTY*/
        tempVar = tempVar.multiply(qty);
        tempFix = tempFix.multiply(qty);
        tempTotal = tempTotal.multiply(qty);

        /*SET TO OBJ*/
        fgStockCardAccounting.setAmountFixCost(tempFix);
        fgStockCardAccounting.setAmountVarCost(tempVar);
        fgStockCardAccounting.setAmountTotalCost(tempTotal);
        
        return fgStockCardAccounting;
    }
    
}
