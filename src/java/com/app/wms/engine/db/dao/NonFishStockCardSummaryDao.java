package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.NonFishStockCardSummary;
import java.util.List;

public interface NonFishStockCardSummaryDao {
    
    public int insert(NonFishStockCardSummary nfSummary);
    
    public List<NonFishStockCardSummary> findAll();
    
    public List<NonFishStockCardSummary> findByItemCategory(String productCategory);
    
    public List<NonFishStockCardSummary> findByItemCategoryandDate(String productCategory, String asOfDate);
    
}
