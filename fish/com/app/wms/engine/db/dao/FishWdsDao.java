package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishWds;
import java.math.BigDecimal;

public interface FishWdsDao extends GeneralDao<FishWds> {

    public List<FishWds> findAllByWdsNo(String wdsNo);

    public List<FishWds> findAllAndPaging(int limit, int offset);

    public List<FishWds> searchAndPaging(String wdsNo, Date wdsDate, int limit, int offset);

    public List<FishWds> searchAndPagingWithoutDate(String wdsNo, int limit, int offset);

    public Boolean checkIsWdsNoExist(String wdsNo);
    
    /* GNVS | Interface Here */
    public void updateFishBalanceActual(int vesselId, int storageId, int fishId, BigDecimal deduct, BigDecimal add, String updatedBy, String wdsNo);
    
}
