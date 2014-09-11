/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishTransaction;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Faridzi
 */
public interface FishTransactionDao {

    /*FIND TRANSACTION IN*/
    public List<FishTransaction> findByTransactionIn(Integer fishSupplierId, Integer fishId, Date date);

    /*FIND TRANSACTION OUT*/
    public List<FishTransaction> findByTransactionOut(Integer fishSupplierId, Integer fishId, Date date);
    
    /*FIND TRANSACTION IN THIS MONTH*/
    public List<FishTransaction> findByTransactionInThisMonth(Integer fishSupplierId, Integer fishId, Date date);
    
    /*FIND TRANSACTION OUT THIS MONTH*/
    public List<FishTransaction> findByTransactionOutThisMonth(Integer fishSupplierId, Integer fishId, Date date);
    
}
