package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DrDtl implements Serializable {
    
    private int id;
    
    private int drCode;
    
    private BigDecimal drQty;
    
    private String drUom;
    
    private BigDecimal drUnitCost;
    
    private String productCode;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.DrDtl: " );
        sb.append("id = ").append( getId() );
        sb.append(", drCode = ").append( getDrCode() );
        sb.append(", drQty = ").append( getDrQty() );
        sb.append(", drUom = ").append( getDrUom() );
        sb.append(", drUnitCost = ").append( getDrUnitCost());
        sb.append(", productCode = ").append( getProductCode() );
        sb.append(", createdBy = ").append( getCreatedBy());
        sb.append(", createdDate = ").append( getCreatedDate());
        sb.append(", updatedBy = ").append( getUpdatedBy());
        sb.append(", updatedDate = ").append( getUpdatedDate());
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrCode() {
        return drCode;
    }

    public void setDrCode(int drCode) {
        this.drCode = drCode;
    }

    public BigDecimal getDrQty() {
        return drQty;
    }

    public void setDrQty(BigDecimal drQty) {
        this.drQty = drQty;
    }

    public String getDrUom() {
        return drUom;
    }

    public void setDrUom(String drUom) {
        this.drUom = drUom;
    }

    public BigDecimal getDrUnitCost() {
        return drUnitCost;
    }

    public void setDrUnitCost(BigDecimal drUnitCost) {
        this.drUnitCost = drUnitCost;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
}
