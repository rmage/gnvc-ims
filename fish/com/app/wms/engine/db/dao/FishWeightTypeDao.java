package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishWeightType;
import java.util.List;

public interface FishWeightTypeDao extends GeneralDao<FishWeightType> {
    
    public List<FishWeightType> findAllAndPaging(int limit, int offset);
    public List<FishWeightType> searchAndPaging(String code, int limit, int offset);
}
