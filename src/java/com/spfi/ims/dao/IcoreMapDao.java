package com.spfi.ims.dao;

import com.app.wms.engine.db.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IcoreMapDao {
    
    public List<Map<String, Object>> findWhereProductCodeEquals(String productCategory, String source);
    
    public void insertNUpdate(String productCode, String hsCode, String sourcePro, String createdDate, String createdBy, String updatedDate, String updatedBy, String isActive);
    
    public List<Map<String, Object>> findProductCategory();
}
