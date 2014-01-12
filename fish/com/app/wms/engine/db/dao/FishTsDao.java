package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishTs;

public interface FishTsDao extends GeneralDao<FishTs> {
	public List<FishTs> findAllAndPaging(int limit, int offset);
	public Boolean checkIsTsNoExist(String tsNo);
}
