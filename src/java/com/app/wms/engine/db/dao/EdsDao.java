package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Eds;
import java.util.List;

public interface EdsDao {
    
    public void insert(Eds e);
    
    public List<Eds> findAll();
    
}
