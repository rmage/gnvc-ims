package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.PurchaseDtl;
import java.util.List;

public interface PurchaseDtlDao {
    
    public void insert(PurchaseDtl pd);
    
    public List<PurchaseDtl> findByPo(int poCode);
    
}
