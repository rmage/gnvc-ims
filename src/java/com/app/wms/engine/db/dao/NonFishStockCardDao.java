package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.NonFishDocumentSummary;
import com.app.wms.engine.db.dto.NonFishStockCardAccounting;
import java.util.List;

public interface NonFishStockCardDao {
    
    public int insert(NonFishStockCardAccounting nfs);
    
    public int update(NonFishStockCardAccounting nfs);
    
    public boolean isExist(NonFishStockCardAccounting nfs);
    
    public Integer exist(NonFishStockCardAccounting nfs);
    
    public List<NonFishStockCardAccounting> findByProductCategoryAndDateAndDoctype(String productCategory, String date, String docType);
    
    public List<NonFishDocumentSummary> findPerDocumentType(String productCategory, String date, String docType);
    
    public List<NonFishStockCardAccounting> findSummaryRR(String productCategory, String date);
    
    public List<NonFishStockCardAccounting> findSummaryTS(String productCategory, String date);
    
    public List<NonFishStockCardAccounting> findSummaryDR(String productCategory, String date);
    
}
