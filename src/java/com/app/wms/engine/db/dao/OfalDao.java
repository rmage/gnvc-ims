package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Ofal;
import java.util.List;

public interface OfalDao {
    
    public int insert(Ofal o);
    
    public List<Ofal> findAll();
    
}
