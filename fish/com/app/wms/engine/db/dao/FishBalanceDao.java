package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishBalance;
import com.app.wms.engine.db.exceptions.DaoException;

public interface FishBalanceDao extends GeneralDao<FishBalance> {

    public FishBalance findUniqueFishBalance(int vesselId, int storageId, int fishId);

    public FishBalance findUniqueFishBalanceActual(int vesselId, int storageId, int fishId);

    public List<FishBalance> findBalanceByVesselId(int vesselId);
    
    /* GNVS | Actual Balance */

    public int insertActual(FishBalance dto);
    
    public void updateActual(int id, FishBalance dto) throws DaoException;
            
    public FishBalance findByPrimaryKeyActual(int id) throws DaoException;
    
    public List<FishBalance> getWithdrawableFish(int vesselId);
    
}
