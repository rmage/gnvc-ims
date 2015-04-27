package com.spfi.ims.dao;

import com.spfi.ims.dto.FishReclassification;
import com.spfi.ims.dto.FishReclassificationDetail;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FishReclassificationDao {
    
    public int insert(FishReclassification fr);
    
    public void insertD(FishReclassificationDetail frd);
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> getBatchInfo(String batchNo);
    
    public List<Map<String, Object>> getFish();
    
    public List<Map<String, Object>> getFishStorage();
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(int key, String updatedBy);
    
    public List<Map<String, Object>> getReclassification(int key);
    
}
