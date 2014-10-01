package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.Purchase;
import com.app.wms.engine.db.dto.PurchaseDtl;
import com.app.wms.engine.db.dto.ReceiveReport;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReceiveReportDao {

    public void insert(ReceiveReport rr);

    public void updateStockInventory(String productCode, int qty);

    public List<Purchase> findByNotInRR();

    public List<PurchaseDtl> findByPo(int poCode);

    public List<ReceiveReport> findAll();
    
    public List<String> findProductCodeWithRR(String productCategory, Date asOf);

    public int ajaxMaxPage(BigDecimal show, String where);

    public List<Map<String, Object>> ajaxSearch(int page, int show, String where, String order);

    public List<Map<String, Object>> getPo(String poCode);

    public List<Map<String, Object>> getPoDetail(String poCode);
    
    public void insert(String data, String createdBy);
    
    public List<ReceiveReport> findByProductCode(String productCode, String asOf);
    
    public List<ReceiveReport> findByProductCodeAndBeforeThan(String productCode, String asOf);

}
