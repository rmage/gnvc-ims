package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Purchase implements Serializable {
    
    private int poCode;
    
    private int rateId;
    
    private Date poDate;
    
    private String supplierCode;
    
    private int discount;
    
    private int pph;
    
    private int ppn;
    
    private String currency;
    
    private String remarks;
    
    private String isApproved;
    
    private String approvedBy;
    
    private Date approvedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.Purchase: " );
        sb.append("poCode = ").append( getPoCode());
        sb.append(", poDate = ").append( getPoDate());
        sb.append(", supplierCode = ").append( getSupplierCode());
        sb.append(", pph = ").append( getPph());
        sb.append(", ppn = ").append( getPpn());
        sb.append(", isApproved = ").append( getIsApproved());
        sb.append(", approvedBy = ").append( getApprovedBy());
        sb.append(", approvedDate = ").append( getApprovedDate());
        sb.append(", createdBy = ").append( getCreatedBy());
        sb.append(", createDate = ").append( getCreatedDate());
        sb.append(", updatedBy = ").append( getUpdatedBy());
        sb.append(", updatedDate = ").append( getUpdatedDate());
        return sb.toString();
    }

    public int getPoCode() {
        return poCode;
    }

    public void setPoCode(int poCode) {
        this.poCode = poCode;
    }

    public Date getPoDate() {
        return poDate;
    }

    public void setPoDate(Date poDate) {
        this.poDate = poDate;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPph() {
        return pph;
    }

    public void setPph(int pph) {
        this.pph = pph;
    }

    public int getPpn() {
        return ppn;
    }

    public void setPpn(int ppn) {
        this.ppn = ppn;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

}
