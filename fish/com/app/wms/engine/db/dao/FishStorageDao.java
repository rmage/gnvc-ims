package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishStorage;
import java.util.List;

public interface FishStorageDao extends GeneralDao<FishStorage> {
    public List<FishStorage> findAllAndPaging(int limit, int offset);
    public List<FishStorage> searchAndPaging(String code, int limit, int offset);
}
