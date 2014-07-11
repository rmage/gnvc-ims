package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishBalanceHistory;

public interface FishBalanceHistoryDao extends GeneralDao<FishBalanceHistory> {
    
    /* GNVS | Actual Balance */
    
    public int insertActual(FishBalanceHistory dto);
    
}
