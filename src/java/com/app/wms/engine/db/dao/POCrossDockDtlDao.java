package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.POCrossDockDtl;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public interface POCrossDockDtlDao {
    
    public void insert(POCrossDockDtl dto);
    
    public void update(POCrossDockDtl dto);
    
    public List<POCrossDockDtl> findbyPOCode(int poCode);
    
    /*
    
    public List<POCrossDockDtl> findbyPOCodeApproved(int poCode);*/
    
    public List<POCrossDockDtl> findbyPOCodeCrossDock(String poCode);
    
    public List<POCrossDockDtl> findbyGRCode(String grCode);
    
    public List<POCrossDockDtl> findbySOCode(String soCode);
    
}
