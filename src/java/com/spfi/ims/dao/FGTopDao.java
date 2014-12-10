package com.spfi.ims.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FGTopDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public Map<String, Object> findById(int id);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> findAllActive();
    
    public void insert(String data, String createdBy);
    
    public void edit(int key, String data, String updatedBy);
    
    public void delete(int key, String updatedBy);
    
}
