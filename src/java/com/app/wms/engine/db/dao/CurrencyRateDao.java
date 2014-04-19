package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.CurrencyRate;
import java.util.List;

public interface CurrencyRateDao {
    
    public int insert(CurrencyRate cr);
    
    public CurrencyRate findByCurrency(String currencyCode);
    
    public CurrencyRate findByPurchase(int poCode);
    
    public List<CurrencyRate> findAll();
    
}
