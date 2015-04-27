package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishSpoilage;
import com.app.wms.engine.db.exceptions.DaoException;
import java.math.BigDecimal;
import java.util.Map;

public interface FishSpoilageDao {

    public int insert(FishSpoilage dto) throws DaoException;

    public void update(int id, FishSpoilage dto) throws DaoException;

    public void delete(int id) throws DaoException;

    public FishSpoilage findByPrimaryKey(int id) throws DaoException;

    public List<FishSpoilage> findAll() throws DaoException;

    public List<FishSpoilage> findByDateRange(Date dateFrom, Date dateTo) throws DaoException;

    public List<FishSpoilage> findByBatchNumber(String batchNumber) throws DaoException;

    public List<FishSpoilage> findAllDistinct();

    public List<FishSpoilage> findAllDistinctAndPaging(int limit, int offset);

    public List<FishSpoilage> searchDistinctAndPaging(String batchNo, Date dateShift, int limit, int offset);

    public List<FishSpoilage> searchDistinctAndPagingWithoutDate(String batchNo, int limit, int offset);

    public List<FishSpoilage> findAllForReport(int vesselId, Date dateShift, String timeShift);

    // UPDATED | FYA | October 23, 2014
    public int ajaxMaxPage(BigDecimal show, String where);

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);

    public void insert2(String data, String createdBy);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(String key, String updatedBy);
    
    public List<Map<String, Object>> getSpoilage(String key);
    
}
