package com.spfi.ims.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccountingCutOffDao {
    
    public Map<String, Object> getHeaderData();
    
    public List<Map<String, Object>> getDetailData(String dateFrom, String dateTo, String storage, BigDecimal currencyRate);
    
    public void saveDetailData(String dateFrom, String dateTo, String storage, BigDecimal currencyRate, String createdBy);
    
}
