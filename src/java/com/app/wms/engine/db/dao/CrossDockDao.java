package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.CrossDock;
import java.util.Date;
import java.util.List;

public interface CrossDockDao {
    
    public void insert(CrossDock dto);
    
    public void update(CrossDock dto);
    
    public List<CrossDock> findAll(String whCode, int page);
    
    public CrossDock findByCDCode(String cdCode);
    
    public List<CrossDock> findByCDDate(Date cdDateIn);
    
}