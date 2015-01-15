package com.spfi.ims.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FGBookedOrder15Dao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public Map<String, Object> findByNumber(String borNumber);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> findDtlByNumber(String borNumber);
    
    public void delete(String key, String createdBy);
    
    public void edit(String data, String createdBy);
    
    public void insert(String data, String createdBy);
    
}
