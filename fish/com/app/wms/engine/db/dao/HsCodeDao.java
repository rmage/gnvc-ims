package com.app.wms.engine.db.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface HsCodeDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(String key, String updatedBy);
    
    public List<Map<String, Object>> getHsCode(String code);
    
    public List<Map<String, Object>> findHsCodebyCode(String code);

}
