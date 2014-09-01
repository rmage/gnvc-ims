package com.spfi.ims.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FGOrderFillDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> getBor(String borCode);
    
    public List<Map<String, Object>> getPalletTransfer(String ptsCode);
    
    public void insert(String data, String createdBy);
    
}