package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishBadStock;

public interface FishBadStockDao extends GeneralDao<FishBadStock> {
	public List<FishBadStock> findAllAndPaging(int limit, int offset);
	public List<FishBadStock> searchAndPaging(String bsNo, Date bsDate, int limit, int offset);
}
