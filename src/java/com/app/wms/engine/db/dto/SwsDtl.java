package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SwsDtl implements Serializable {
    
    private int id;
    
    private String swsCode;
    
    private String productCode;
    
    private BigDecimal qty;
    
    private BigDecimal soh;
    
    private String uom;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.SwsDtl: " );
        sb.append("id = ").append( getId());
        sb.append(", swsCode = ").append( getSwsCode());
        sb.append(", productCode = ").append( getProductCode());
        sb.append(", qty = ").append( getQty());
        sb.append(", soh = ").append( getSoh());
        sb.append(", uom = ").append( getUom());
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

    public String getSwsCode() {
        return swsCode;
    }

    public void setSwsCode(String swsCode) {
        this.swsCode = swsCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getSoh() {
        return soh;
    }

    public void setSoh(BigDecimal soh) {
        this.soh = soh;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
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
