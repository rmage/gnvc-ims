package com.spfi.ims.dao;

import com.spfi.ims.dto.FishMoving;
import com.spfi.ims.dto.FishMovingDetail;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface FishMovingDao {
    
    public int insert(FishMoving fm);
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> getBatchInCs(int storageId);
    
    public List<Map<String, Object>> getFishStorage();
    
    public List<Map<String, Object>> getFishInCsBatch(int storageId, int vesselId);
    
    public void insertD(FishMovingDetail fmd);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(int key, String updatedBy);
    
    public List<Map<String, Object>> getMoving(int key);
    
}
