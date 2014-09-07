package com.spfi.ims.dao;

import java.util.List;
import java.util.Map;

public interface D3Dao {
    
    public List<Map<String, Object>> NFUnitPriceTrend (String itemCode, String currencyCode, String dateFrom, String dateTo);
    
    public List<Map<String, Object>> NFPriceList (String itemCode, String dateFrom, String dateTo);
    
}
