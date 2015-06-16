package com.spfi.ims.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BcCodeDao {
    
    public int ajaxMaxPage(BigDecimal show, String where);
    
    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);
    
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(String key, String updatedBy);
   
   public List<Map<String, Object>> findWhereTrxNo(String rrCode, String module, int mode);
   
   public List<Map<String, Object>> getBcCode(String idBc);
   
   public List<Map<String, Object>> findCodeBc();
   
}
