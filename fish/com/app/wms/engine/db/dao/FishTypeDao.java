package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishType;
import java.util.List;

public interface FishTypeDao extends GeneralDao<FishType> {
    public List<FishType> findAllAndPaging(int limit, int offset);
    public List<FishType> searchAndPaging(String code, int limit, int offset);
    public List<FishType> findByCode(String code);
}
