package com.spfi.ims.dao;

import com.app.wms.engine.db.dto.FGItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FGItemDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public Map<String, Object> findById(int id);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> findByItemCode(String itemCode);
    
    public List<Map<String, Object>> findByItemCodeOnTest(String itemCode);
    
    public List<Map<String, Object>> getPackStyle();
    
    public void insert(String data, String createdBy);
    
    public void edit(int key, String data, String updatedBy);
    
    public void delete(int key, String updatedBy);
    
    public List<FGItem> findAllActive();
    
    public FGItem findById(Integer id);
    
}
