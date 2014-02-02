package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishWdsDetail;

public interface FishWdsDetailDao extends GeneralDao<FishWdsDetail> {

	public List<FishWdsDetail> findAllByWdsId(int wdsId);
    public void deleteAllByWdsId(int wdsId);
}
