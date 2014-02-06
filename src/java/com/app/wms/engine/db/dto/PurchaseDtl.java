package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseDtl implements Serializable {
    
    private int id;
    
    private int poCode;
    
    private String prsNumber;
    
    private String productCode;
    
    private String departmentCode;
    
    private BigDecimal subTotal;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.PurchaseDtl: " );
        sb.append("id = ").append( getId());
        sb.append(", poCode = ").append( getPoCode());
        sb.append(", prsNumber = ").append( getPrsNumber());
        sb.append(", productCode = ").append( getProductCode());
        sb.append(", departmentCode = ").append( getDepartmentCode());
        sb.append(", subTotal = ").append( getSubTotal());
        sb.append(", createdBy = ").append( getCreatedBy());
        sb.append(", createDate = ").append( getCreatedDate());
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

    public int getPoCode() {
        return poCode;
    }

    public void setPoCode(int poCode) {
        this.poCode = poCode;
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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
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
