package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Dr;
import java.math.BigDecimal;
import java.util.List;

public interface DrDao {
    
    public void insert(Dr d);
    
    public void updateStockInventory(String productCode, BigDecimal qty);
    
    public List<Dr> findAll(String type);

}
