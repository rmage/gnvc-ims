package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishWsDetail;

public interface FishWsDetailDao extends GeneralDao<FishWsDetail> {

	public List<FishWsDetail> findByWsId(int wsId);
	public List<FishWsDetail> findByWsDetailIds(String wsDetailIds);
	public List<FishWsDetail> findByWsIdGroupByFish(int wsId);
}
