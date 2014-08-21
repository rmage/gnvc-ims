package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReceiveReportDtl implements Serializable {
    
    private int id;
    
    private String rrCode;
    
    private String productCode;
    
    private String departmentCode;
    
    private int qtyGood;
    
    private int qtyBad;
    
    private String uom;
    
    private BigDecimal unitCost;
    
    private BigDecimal amount;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.ReceiveReportDtl: " );
        sb.append("id = ").append(getId());
        sb.append(", rrCode = ").append(getRrCode());
        sb.append(", productCode = ").append(getProductCode());
        sb.append(", departmentCode = ").append(getDepartmentCode());
        sb.append(", qtyGood = ").append(getQtyGood());
        sb.append(", qtyBad = ").append(getQtyBad());
        sb.append(", uom = ").append(getUom());
        sb.append(", unitCost = ").append(getUnitCost());
        sb.append(", amount = ").append(getAmount());
        sb.append(", createdBy = ").append(getCreatedBy());
        sb.append(", createDate = ").append(getCreatedDate());
        sb.append(", updatedBy = ").append(getUpdatedBy());
        sb.append(", updatedDate = ").append(getUpdatedDate());
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRrCode() {
        return rrCode;
    }

    public void setRrCode(String rrCode) {
        this.rrCode = rrCode;
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

    public int getQtyGood() {
        return qtyGood;
    }

    public void setQtyGood(int qtyGood) {
        this.qtyGood = qtyGood;
    }

    public int getQtyBad() {
        return qtyBad;
    }

    public void setQtyBad(int qtyBad) {
        this.qtyBad = qtyBad;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
