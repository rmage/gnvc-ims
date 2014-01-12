package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishBadStockDetail;

public interface FishBadStockDetailDao extends GeneralDao<FishBadStockDetail> {

	List<FishBadStockDetail> findAllByBsId(int bsId);
}
