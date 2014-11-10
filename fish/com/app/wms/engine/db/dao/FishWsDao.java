package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishWs;
import java.math.BigDecimal;
import java.util.Map;

public interface FishWsDao extends GeneralDao<FishWs> {

    public List<FishWs> findByVesselIdAndDateShift(int vesselId, Date dateShift);

    public List<FishWs> findAllAndPaging(int limit, int offset);

    public List<FishWs> searchAndPaging(String wsNo, Date wsDate, int limit, int offset);

    public List<FishWs> searchAndPagingWithoutDate(String wsNo, int limit, int offset);

    public Boolean checkIsWsNoExist(String wsNo);

    /*ACCOUNTING METHOD*/
    public List<FishWs> findWSWithinDateAndFishType(String dateString, String fishType);

    // UPDATE | FYA | October 22, 2014
    public int ajaxMaxPage(BigDecimal show, String where);

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);

    public void insert2(String data, String createdBy);

}
