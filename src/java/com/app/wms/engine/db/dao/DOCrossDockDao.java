package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.DOCrossDock;
import java.util.Date;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public interface DOCrossDockDao {
    
    public void insert(DOCrossDock dto);
    
    public void update(DOCrossDock dto);
    
    public List<DOCrossDock> findAll(String whCode, int page);
    
    public DOCrossDock findByDOCode(String doCode);
    
    public List<DOCrossDock> findByDODate(Date doDate);

}
