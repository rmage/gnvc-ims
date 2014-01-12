package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.POCrossDock;
import java.util.Date;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public interface POCrossDockDao {
    
    public Integer insert(POCrossDock dto);
    
    public void update(POCrossDock dto);
    
    public void approved(String paCode, String approvedBy, String type);
    
    public List<POCrossDock> findAll(String whCode, int page);
    
    public List<POCrossDock> findPONotInGR(String whCode, String sidx, String sord);
    
    public List<POCrossDock> findPONotInSO(String whCode, String sidx, String sord);
    
    public POCrossDock findByPOCode(int poCode);
    
    public POCrossDock findByPONumber(String poNumber);
    
    public List<POCrossDock> findByPODate(Date poDate);
    
    public POCrossDock findByPACode(String paCode);
    
}
