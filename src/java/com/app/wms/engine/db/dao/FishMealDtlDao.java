package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishMealDtl;
import java.util.List;

public interface FishMealDtlDao {
    
    public int insert(FishMealDtl fmd);
    
    public void update(FishMealDtl fmd);
    
    public FishMealDtl findLastDate(int year, int month);
    
    public List<FishMealDtl> findCurrentMonth(int year, int month, int endDate);
    
}
