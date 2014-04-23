package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishOilDtl;
import java.util.List;

public interface FishOilDtlDao {
    public List<FishOilDtl> findCurrentMonth(int year, int month, int endDate);
    public FishOilDtl findLastDate(int year, int month);
    public int insert(FishOilDtl fod);
    public int update(FishOilDtl fod);
}
