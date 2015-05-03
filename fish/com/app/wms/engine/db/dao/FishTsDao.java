package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishTs;
import java.math.BigDecimal;
import java.util.Map;

public interface FishTsDao extends GeneralDao<FishTs> {

    public List<FishTs> findAllAndPaging(int limit, int offset);

    public List<FishTs> searchAndPaging(String tsNo, Date tsDate, int limit, int offset);

    public List<FishTs> searchAndPagingWithoutDate(String tsNo, int limit, int offset);

    public Boolean checkIsTsNoExist(String tsNo);
    
    // UPDATED | FYA | October 28, 2014
    public int ajaxMaxPage(BigDecimal show, String where);

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);

    public void insert2(String data, String createdBy);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(int key, String updatedBy);
    
    public List<Map<String, Object>> getTransfer(int key);
    
}
