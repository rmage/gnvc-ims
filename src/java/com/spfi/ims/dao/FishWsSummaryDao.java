package com.spfi.ims.dao;

import com.spfi.ims.dto.FishWsSummary;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FishWsSummaryDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> getBatchInfo(String batchNo);
    
    public void insert(FishWsSummary fws);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(int key, String updatedBy);
    
    public List<Map<String, Object>> getWSSummary(int key);
    
}
