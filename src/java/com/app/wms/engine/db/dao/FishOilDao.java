package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishOil;

public interface FishOilDao {
    public int insert(FishOil fo);
    public boolean testFirstUse();
    public String findAdjusted(String date, int b);
    public int insertFirstUse(int drums, int kilos, String userId);
    public void insertOrUpdateAdjustFM(int drums, int kilos, String date, int b);
}
