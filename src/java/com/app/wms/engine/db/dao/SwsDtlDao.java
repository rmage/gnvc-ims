package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.SwsDtl;
import java.util.List;

public interface SwsDtlDao {

    public void insert(SwsDtl sd);
    
    public List<SwsDtl> findBySws(int swsCode);

}
