package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishWdsDetail;

public interface FishWdsDetailDao extends GeneralDao<FishWdsDetail> {

	List<FishWdsDetail> findAllByWdsId(int wdsId);
}
