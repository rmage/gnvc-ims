package com.app.wms.engine.db.dao;

import com.app.wms.engine.db.dto.FishUnitCost;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface FishUnitCostDao {

    public int insert(FishUnitCost fc);

    public int update(FishUnitCost fc);
    
    public int updateUnitCost(Integer fcId, BigDecimal unitCost, String updatedBy, Date updatedDate);

    public int delete(FishUnitCost fc);
    
    public boolean isExist(FishUnitCost fc);
    
    public boolean isContractNumberAlreadyUsed(String contractNumber);
    
    public int findBySupplierCodeandFishCode(FishUnitCost fc);

    public int ajaxMaxPage(String where, BigDecimal show);

    public List<FishUnitCost> ajaxSearch(String where, String order, int page, int show);
    
    public List<FishUnitCost> findByContractNumber(String contractNumber);

}
