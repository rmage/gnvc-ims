package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchaseDtl;
import com.app.wms.engine.db.dto.ReceiveReport;
import java.util.List;

public interface ReceiveReportDao {
    
    public void insert(ReceiveReport rr);
    
    public void updateStockInventory(String productCode, int qty);
    
    public List<Purchase> findByNotInRR();
    
    public List<PurchaseDtl> findByPo(int poCode);
    
    public List<ReceiveReport> findAll();
    
}
