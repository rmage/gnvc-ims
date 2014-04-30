package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReturnCargoDrDtl implements Serializable {
    
    private int id;
    
    private String rrCode;
    
    private String productCode;
    
    private String departmentCode;
    
    private BigDecimal rrQty;
    
    private String rrUom;
    
    private BigDecimal rrUnitCost;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        //FIXME :: ReturnCargoDrDtl @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.ReturnCargoDrDtl: " );
        
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public String getRrCode() {
        return rrCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public BigDecimal getRrQty() {
        return rrQty;
    }

    public String getRrUom() {
        return rrUom;
    }

    public BigDecimal getRrUnitCost() {
        return rrUnitCost;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRrCode(String rrCode) {
        this.rrCode = rrCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void setRrQty(BigDecimal rrQty) {
        this.rrQty = rrQty;
    }

    public void setRrUom(String rrUom) {
        this.rrUom = rrUom;
    }

    public void setRrUnitCost(BigDecimal rrUnitCost) {
        this.rrUnitCost = rrUnitCost;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
}
