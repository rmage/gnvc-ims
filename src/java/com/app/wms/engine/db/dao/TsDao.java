package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.Ts;
import java.util.List;

public interface TsDao {
    
    public void insert(Ts t);
    
    public void updateStockInventory(String productCode, int qty);

    public List<Sws> findWhereNotInTs();
    
    public List<Ts> findAll(String module);
    
}
