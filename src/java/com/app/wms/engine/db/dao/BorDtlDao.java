package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.BorDtl;
import java.util.List;

public interface BorDtlDao {

    public void insert(BorDtl bd);
    
    public List<BorDtl> findByBor(String borCode);

}
