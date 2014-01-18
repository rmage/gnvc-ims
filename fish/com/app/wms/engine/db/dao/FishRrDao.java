package com.app.wms.engine.db.dao;

import java.util.Date;
import java.util.List;

import com.app.wms.engine.db.dto.FishRr;

public interface FishRrDao extends GeneralDao<FishRr> {
	public List<FishRr> findAllAndPaging(int limit, int offset);
	public List<FishRr> searchAndPaging(String rrNo, Date rrDate, int limit, int offset);
    public List<FishRr> searchAndPagingWithoutDate(String rrNo, int limit, int offset);
	public Boolean checkIsRrNoExist(String rrNo);
}
