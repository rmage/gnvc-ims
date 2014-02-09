package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.ReceiveReportDtl;
import java.util.List;

public interface ReceiveReportDtlDao {
    
    public void insert(ReceiveReportDtl rrd);
    
    public List<ReceiveReportDtl> findByPoProduct(int poCode, String productCode);
    
    public List<ReceiveReportDtl> findByRr(int rrCode);
    
}
