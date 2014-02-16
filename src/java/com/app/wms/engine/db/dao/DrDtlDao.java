package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.DrDtl;
import java.util.List;

public interface DrDtlDao {

    public void insert(DrDtl dd);
    
    public List<DrDtl> findByDR(int drCode);

}
