package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.ProductPrice;
import com.app.wms.engine.db.dto.PrsDetail;
import java.math.BigDecimal;
import java.util.List;

public interface ProductPriceDao {
    
    public void insert(ProductPrice pp);
    
    public ProductPrice update(ProductPrice pp, int status, BigDecimal qty);
    
    public ProductPrice findByProduct(String productCode);

}
