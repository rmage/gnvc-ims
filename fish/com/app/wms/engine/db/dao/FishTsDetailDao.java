package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishTsDetail;

public interface FishTsDetailDao extends GeneralDao<FishTsDetail> {

	public List<FishTsDetail> findAllByTsId(int tsId);
    public void deleteAllByTsId(int tsId);
}
