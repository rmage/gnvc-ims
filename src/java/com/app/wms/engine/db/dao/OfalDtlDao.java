package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.OfalDtl;
import java.util.List;

public interface OfalDtlDao {
    
    public void insert(OfalDtl od);
    
    public List<OfalDtl> findByOfal(int ofalId);
    
}
