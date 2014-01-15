package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishWs;

public interface FishWsDao extends GeneralDao<FishWs> {

	public List<FishWs> findByVesselIdAndDateShift(int vesselId, Date dateShift);
	public List<FishWs> findAllAndPaging(int limit, int offset);
	public List<FishWs> searchAndPaging(String wsNo, Date wsDate, int limit, int offset);
	public Boolean checkIsWsNoExist(String wsNo);
}
