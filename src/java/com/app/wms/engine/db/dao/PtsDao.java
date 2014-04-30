package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Pts;
import java.math.BigDecimal;
import java.util.List;

public interface PtsDao {
    
    public void insert(Pts p);
    
    public void updateStockInventory(String productCode, BigDecimal qty);
    
    public Pts findByPts(int ptsCode);
    
    public Pts findByPtsUnref(int ptsCode);
    
    public List<Pts> findByBor(String borCode);
    
    public List<Pts> findByBorNotInOfal(String borCode, String brandName);
    
    public List<Pts> findByUser(String userId);
    
}
