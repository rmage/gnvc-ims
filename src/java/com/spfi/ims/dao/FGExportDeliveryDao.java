package com.spfi.ims.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FGExportDeliveryDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
            
    public List<Map<String, Object>> getLabelingMonitoring(String lmCode);
    
    public void insert(String data, String createdBy);
    
}
