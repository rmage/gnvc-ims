package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishRr;
import java.math.BigDecimal;
import java.util.Map;

public interface FishRrDao extends GeneralDao<FishRr> {

    public List<FishRr> findAllAndPaging(int limit, int offset);

    public List<FishRr> searchAndPaging(String rrNo, Date rrDate, int limit, int offset);

    public List<FishRr> searchAndPagingWithoutDate(String rrNo, int limit, int offset);

    public Boolean checkIsRrNoExist(String rrNo);
    
    public FishRr findById(Integer rrId);
    
    /* GNVS | 2014 Update */
    public void insert(String rrNo, Date rrDate, String batchNo, String dateFrom, String dateTo, String type, String wsNo, String createdBy);
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public List<Map<String, Object>> getBatchInfo(String batchNo);
    
    public List<Map<String, Object>> getWeightSlip(String batchNo, String dateFrom, String dateTo, String type);
    
}
