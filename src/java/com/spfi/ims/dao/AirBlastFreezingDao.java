package com.spfi.ims.dao;

import com.spfi.ims.dto.AirBlastFreezing;
import com.spfi.ims.dto.AirBlastFreezingDetail;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AirBlastFreezingDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public int insertH(AirBlastFreezing abf);
    
    public void insertD(AirBlastFreezingDetail abfd, String abfNo, String batchNo, int storageId);
    
    public List<Map<String, Object>> getFish(String batchNo, String wsNo);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public void insert(String data, String createdBy);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(int key, String updatedBy);
    
    public List<Map<String, Object>> getAirBlastFreezing(int key);
    
}
