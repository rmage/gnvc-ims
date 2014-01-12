package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.SOCrossDock;
import java.util.Date;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public interface SOCrossDockDao {

    public void insert(SOCrossDock dto);
    
    public void update(SOCrossDock dto);
    
    public void approved(String pCode, String approvedBy);
    
    public List<SOCrossDock> findAll(String whCode, int page);
    
    public List<SOCrossDock> findSONotInP(String whCode, String sidx, String sord);
    
    public List<SOCrossDock> findSONotInDO(String whCode, String sidx, String sord);
    
    public SOCrossDock findBySOCode(String soCode);
    
    public List<SOCrossDock> findBySODate(Date soDate);
    
}
