package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Bor;
import java.util.List;

public interface BorDao {

    public void insert(Bor b);
    
    public void approval(Bor b, String mode);
    
    public List<Bor> findAll();

}
