package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishWds;
import java.math.BigDecimal;
import java.util.Map;

public interface FishWdsDao extends GeneralDao<FishWds> {

    public List<FishWds> findAllByWdsNo(String wdsNo);

    public List<FishWds> findAllAndPaging(int limit, int offset);

    public List<FishWds> searchAndPaging(String wdsNo, Date wdsDate, int limit, int offset);

    public List<FishWds> searchAndPagingWithoutDate(String wdsNo, int limit, int offset);

    public Boolean checkIsWdsNoExist(String wdsNo);

    /* GNVS | Interface Here */
    public void updateFishBalanceActual(int vesselId, int storageId, int fishId, BigDecimal deduct, BigDecimal add, String updatedBy, String wdsNo);

    // UPDATED | FYA | October 24, 2014
    public int ajaxMaxPage(BigDecimal show, String where);

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);

    public void insert2(String data, String createdBy);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(int key, String updatedBy);
    
    public List<Map<String, Object>> getWithdrawal(int key);

}
