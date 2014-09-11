/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.FishTransaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author Faridzi
 */
public class FishTransactionListMap implements ParameterizedRowMapper<FishTransaction> {

    public FishTransaction mapRow(ResultSet rs, int i) throws SQLException {
        FishTransaction ft = new FishTransaction();
        ft.setFishId(rs.getInt("fish_id"));
        ft.setUnitCost(rs.getBigDecimal("unit_cost"));
        ft.setCurrencyCode(rs.getString("currency_code"));
        ft.setDocNumber(rs.getString("doc_num"));
        ft.setTransactionDate(rs.getDate("doc_date"));
        ft.setFishSupplierId(rs.getInt("supplier_id"));
        ft.setQuantity(rs.getDouble("qty"));

//        /*INSERT FISH*/
//        FishDao fishDao = DaoFactory.createFishDao();
//        Fish f = null;
//        /*INSERT FISH SUPPLIER*/
//        FishSupplierDao fishSupplierDao = DaoFactory.createFishSupplierDao();
//        FishSupplier fishSupplier = new FishSupplier();
//
//        try {
//            f = fishDao.findByPrimaryKey(ft.getFishId());
//            ft.setFish(f);
//
//            fishSupplier = fishSupplierDao.findByPrimaryKey(ft.getFishSupplierId());
//            ft.setFishSupplier(fishSupplier);
//
//        } catch (DaoException ex) {
//            Logger.getLogger(FishTransactionListMap.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return ft;
    }

}
