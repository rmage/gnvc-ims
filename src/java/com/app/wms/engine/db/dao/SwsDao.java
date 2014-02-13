package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Sws;
import java.util.List;

public interface SwsDao {

    public void insert(Sws s);
    
    public List<Sws> findByLogin(String departmentCode);

}
