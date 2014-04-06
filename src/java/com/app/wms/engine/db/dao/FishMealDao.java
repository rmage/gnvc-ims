package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishMeal;

public interface FishMealDao {
    
    public int insert(FishMeal fm);
    
    public boolean testFirstUse();
    
    public int insertFirstUse(int bags, int kilos, String userId);
    
    public void insertOrUpdateAdjustFM(int bags, int kilos, String date, int b);
    
    public String findAdjusted(String date, int b);
    
}
