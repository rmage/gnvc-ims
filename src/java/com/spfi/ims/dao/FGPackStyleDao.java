package com.spfi.ims.dao;

import com.app.wms.engine.db.dto.FGPackStyle;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FGPackStyleDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public Map<String, Object> findById(int id);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public void insert(String data, String createdBy);
    
    public void edit(int key, String data, String updatedBy);
    
    public void delete(int key, String updatedBy);
    
    public List<FGPackStyle> findAllActive();
    
}
