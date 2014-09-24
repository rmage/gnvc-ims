package com.spfi.ims.dao;

import java.util.List;
import java.util.Map;

public interface D3Dao {
    
    public List<Map<String, Object>> NFUnitPriceTrend (String itemCode, String currencyCode, String dateFrom, String dateTo);
    
    public List<Map<String, Object>> NFPriceList (String itemCode, String dateFrom, String dateTo);
    
    /*
     * REPORT PREVIEW
     */
    public List<Map<String, Object>> getFGStockInventoryPreview (String param1, String param2);
    
    public List<Map<String, Object>> getFGActualInventoryPreview (String param1, String param2);
    
}
