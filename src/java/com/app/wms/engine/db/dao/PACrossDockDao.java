package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.PACrossDock;
import com.app.wms.engine.db.dto.POCrossDockDtl;
import java.util.Date;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public interface PACrossDockDao {
    
    public void insert(PACrossDock dto);
    
    public void update(PACrossDock dto);
    
    public void updatePutaway(POCrossDockDtl dto, String whCode);
    
    public void approved(String paCode, String approvedBy);
    
    public List<PACrossDock> findAll(String whCode, int page);
    
    public PACrossDock findByPCode(String pCode);
    
    public PACrossDock findByPACode(String paCode);
    
    public List<PACrossDock> findByPADate(Date paDate);
    
}
