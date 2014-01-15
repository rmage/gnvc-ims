package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishVessel;
import com.app.wms.engine.db.exceptions.DaoException;

public interface FishVesselDao extends GeneralDao<FishVessel> {

	public List<FishVessel> findByBatchNumber(String batchNumber) throws DaoException;
	public List<FishVessel> findByVesselName(String vesselName) throws DaoException;
}
