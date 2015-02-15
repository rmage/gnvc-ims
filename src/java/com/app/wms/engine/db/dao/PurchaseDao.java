package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Purchase;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PurchaseDao {
    
    public void insert(Purchase p);
    
    public void update(Purchase p);
    
    public Purchase findByPo(String poCode);
    
    public AssignCanvassing findByPrsSupplierProduct(String prsCode, String supplierCode, String productCode);
    
    public List<Purchase> findAll();

    public List<AssignCanvassing> findBySupplier(String supplierCode, String userId);
    
    //Modified 23 April 2014
    public int ajaxMaxPage(String where, BigDecimal show);
    public List<Purchase> ajaxSearch(String where, String order, int page, int show);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(int key, String updatedBy);
    
    public List<Map<String, Object>> getPurchaseOrder(String poCode);
    
}
