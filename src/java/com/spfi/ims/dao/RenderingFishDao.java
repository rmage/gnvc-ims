package com.spfi.ims.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RenderingFishDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public Map<String, Object> ajaxPrepare(String date);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public void insert(String data, String createdBy);
    
}
