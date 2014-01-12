package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.FishWds;

public interface FishWdsDao extends GeneralDao<FishWds> {

	public List<FishWds> findAllByWdsNo(String wdsNo);
	public List<FishWds> findAllAndPaging(int limit, int offset);
	public Boolean checkIsWdsNoExist(String wdsNo);
}
