package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Purchase;
import java.util.List;

public interface PurchaseDao {
    
    public void insert(Purchase p);
    
    public void update(Purchase p);
    
    public Purchase findByPo(String poCode);
    
    public List<Purchase> findAll();

    public List<AssignCanvassing> findBySupplier(String supplierCode);

}
