package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Sws;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface SwsDao {

    public void insert(Sws s);
    
    public List<Sws> findByLogin(String departmentCode);
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public String generateNumber(String userId, String departmentCode);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order, String departmentCode);

}
