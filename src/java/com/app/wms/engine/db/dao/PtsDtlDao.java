package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.PtsDtl;
import java.util.List;

public interface PtsDtlDao {
    
    public void insert(PtsDtl pd);
    
    public List<PtsDtl> findByPts(int ptsCode);
    
}
