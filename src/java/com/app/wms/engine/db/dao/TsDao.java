package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Sws;
import com.app.wms.engine.db.dto.Ts;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TsDao {

    public void insert(Ts t);

    public void updateStockInventory(String productCode, BigDecimal qty);

    public List<Sws> findWhereNotInTs();

    public List<Ts> findAll(String module);
    
    public List<Ts> findByProductCode(String productCode, String asOf);
    
    public List<Ts> findByProductCodeAndBeforeThan(String productCode, String asOf);

    public int ajaxMaxPage(BigDecimal show, String where);

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);

    public List<Map<String, Object>> findSwsDtlForTs(String swsCode);
    
    public List<String> findProductCodeWithRR(String productCategory, Date asOf);
    
    // 2015 Update | by FYA
    public void ajaxNUpdate(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNUpdateO(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNSave(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void ajaxNSaveO(String data, String separatorColumn, String separatorRow, String createdBy);
    
    public void delete(String key, String updatedBy);
    
    public List<Map<String, Object>> getTransfer(String tsCode);

}
