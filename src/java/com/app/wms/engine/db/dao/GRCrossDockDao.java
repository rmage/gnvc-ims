package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.GRCrossDock;
import java.util.Date;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public interface GRCrossDockDao {
    
    public void insert(GRCrossDock dto);
    
    public void update(GRCrossDock dto);
    
    public void approved(String paCode, String updatedBy);
    
    public List<GRCrossDock> findAll(String whCode, int page);
    
    public List<GRCrossDock> findGRNotInPA(String whCode, String sidx, String sord);
    
    public List<GRCrossDock> findByGRDate(Date grDate);
    

    public GRCrossDock findByGRCode(String grCode);
    
}
