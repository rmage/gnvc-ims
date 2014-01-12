package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishBalance;

public interface FishBalanceDao extends GeneralDao<FishBalance> {
	public FishBalance findUniqueFishBalance(int vesselId, int storageId, int fishId);
	public List<FishBalance> findBalanceByVesselId(int vesselId);
}
