/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spfi.ims.dao;

import com.app.wms.engine.db.dto.FGStockCardAccounting;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Faridzi
 */
public interface FGStockCardDao {

    public List<FGStockCardAccounting> findByDateAndPackId(Integer packId, Date date, String currencyType);

}
