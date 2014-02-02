package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssignCanvassing implements Serializable {
    
    private int id;
    
    private String prsNumber;
    
    private String productCode;
    
    private String supplierCode;
    
    private BigDecimal unitPrice;
    
    private String top;
    
    private String topDesc;
    
    private String tod;
    
    private Date wp;
    
    private String isSelected;
    
    private String createdBy;
    
    private Date createDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.AssignCanvassing: " );
        sb.append("id = ").append( getId());
        sb.append(", prsNumber = ").append( getPrsNumber());
        sb.append(", productCode = ").append( getProductCode());
        sb.append(", supplierCode = ").append( getSupplierCode());
        sb.append(", unitPrice = ").append( getId());
        sb.append(", top = ").append( getTop());
        sb.append(", topDesc = ").append( getTopDesc());
        sb.append(", tod = ").append( getTod());
        sb.append(", wp = ").append( getId());
        sb.append(", isSelected = ").append( getIsSelected());
        sb.append(", createdBy = ").append( getCreatedBy());
        sb.append(", createDate = ").append( getCreateDate());
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

    public String getPrsNumber() {
        return prsNumber;
    }

    public void setPrsNumber(String prsNumber) {
        this.prsNumber = prsNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getTopDesc() {
        return topDesc;
    }

    public void setTopDesc(String topDesc) {
        this.topDesc = topDesc;
    }

    public String getTod() {
        return tod;
    }

    public void setTod(String tod) {
        this.tod = tod;
    }

    public Date getWp() {
        return wp;
    }

    public void setWp(Date wp) {
        this.wp = wp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
    
}
