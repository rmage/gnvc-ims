package com.spfi.ims.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FGPalletDispositionDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> getPalletTransfer(String ptsCode);
    
    public void insert(String data, String createdBy);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(int key, String updatedBy);
    
    public List<Map<String, Object>> getPalletDisposition(int key);
    
}
