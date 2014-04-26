package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.AssignCanvassing;
import com.app.wms.engine.db.dto.Supplier;
import java.math.BigDecimal;
import java.util.List;

public interface AssignCanvassingDao {
    
    public int insert(AssignCanvassing ac);
    
    public void update(AssignCanvassing ac);
    
    public AssignCanvassing findForPriceSaving(String prsNumber, String itemCode, String supplierCode);
    
    public List<AssignCanvassing> findByUserId(String userId);
    
    public List<AssignCanvassing> findByUserIdPA(String userId);
    
    public List<AssignCanvassing> findByPrsNumberItemCode(String prsNumber, String itemCode);
    
    public List<AssignCanvassing> findByPrsNumberItemCodeSupplierCode(String prsNumber, String itemCode, String supplierCode);
    
    public List<AssignCanvassing> findForPriceAssign(String supplierCode);
    
    public List<Supplier> findForCanvasingForm(String userId);
    
    //Modified 24 April 2014
    public int ajaxMaxPage(String where, BigDecimal show, String userId);
    public List<AssignCanvassing> ajaxSearch(String where, String order, int page, int show, String userId);
}
