package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishRrDetail;

public interface FishRrDetailDao extends GeneralDao<FishRrDetail> {
	public List<FishRrDetail> findAllByRrId(int rrId);
	public List<FishRrDetail> findByRrIdGroupByFishAndStorage(int rrId);
}
