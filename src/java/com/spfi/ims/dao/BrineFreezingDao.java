package com.spfi.ims.dao;

import com.spfi.ims.dto.BrineFreezing;
import com.spfi.ims.dto.BrineFreezingDetail;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BrineFreezingDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public int insertH(BrineFreezing bf);
    
    public void insertD(BrineFreezingDetail bfd, String bfNo, String batchNo, int storageId);
    
    public List<Map<String, Object>> getFish(String batchNo, String wsNo);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public void insert(String data, String createdBy);
    
}
