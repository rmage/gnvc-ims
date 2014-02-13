package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishWSType;
import java.util.List;

public interface FishWsTypeDao extends GeneralDao<FishWSType> {
	public List<FishWSType> findAllAndPaging(int limit, int offset);
    public List<FishWSType> searchAndPaging(String code, int limit, int offset);
    public boolean checkWsTypeIsExist(String wsType);
    public FishWSType findByTypeCode(String typeCode);
}
