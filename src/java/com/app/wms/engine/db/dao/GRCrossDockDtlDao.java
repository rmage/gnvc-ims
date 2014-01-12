package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.GRCrossDockDtl;
import java.util.List;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public interface GRCrossDockDtlDao {
    
    public void insert(GRCrossDockDtl dto);
    
    public void update(GRCrossDockDtl dto);
    
    public List<GRCrossDockDtl> findbyGRCode(String grCode);

}
