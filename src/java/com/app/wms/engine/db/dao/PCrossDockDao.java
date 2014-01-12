package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.PCrossDock;
import java.util.Date;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public interface PCrossDockDao {
    
    public void insert(PCrossDock dto);
    
    public void update(PCrossDock dto);
    
    public void approved(String pCode, String approvedBy);
    
    public List<PCrossDock> findAll(String whCode, int page);
    
    public PCrossDock findByPCode(String pCode);

    public List<PCrossDock> findByPDate(Date pDate);
    
}
