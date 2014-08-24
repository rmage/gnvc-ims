package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Fish;
import java.util.List;

public interface FishDao extends GeneralDao<Fish> {
    public List<Fish> findAllAndPaging(int limit, int offset);
    public List<Fish> searchAndPaging(String fishCode, int limit, int offset);
    public boolean checkFishCodeIsExist(String code);
    public List<Fish> findFishByBatchNo(String batchNo);
    public List<Fish> findAllActiveAndNotDelete();
}
