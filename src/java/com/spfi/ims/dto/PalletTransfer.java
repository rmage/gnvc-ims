package com.spfi.ims.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PalletTransfer implements Serializable {
    
    private String code;
    
    private String productCode;
    
    private Date date;
    
    private int packSize;
    
    private String canCode;
    
    private String forBrand;
    
    private String reff;
    
    private String location;
    
    private BigDecimal qty;
    
    private String issuedBy;
    
    private Date issuedDate;
    
    private String checkedBy;
    
    private Date checkedDate;
    
    private String transferedBy;
    
    private Date transferedDate;
    
    private String receivedBy;
    
    private Date receivedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.spfi.ims.dto.PalletTransfer: " );
        return sb.toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPackSize() {
        return packSize;
    }

    public void setPackSize(int packSize) {
        this.packSize = packSize;
    }

    public String getCanCode() {
        return canCode;
    }

    public void setCanCode(String canCode) {
        this.canCode = canCode;
    }

    public String getForBrand() {
        return forBrand;
    }

    public void setForBrand(String forBrand) {
        this.forBrand = forBrand;
    }

    public String getReff() {
        return reff;
    }

    public void setReff(String reff) {
        this.reff = reff;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public Date getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(Date checkedDate) {
        this.checkedDate = checkedDate;
    }

    public String getTransferedBy() {
        return transferedBy;
    }

    public void setTransferedBy(String transferedBy) {
        this.transferedBy = transferedBy;
    }

    public Date getTransferedDate() {
        return transferedDate;
    }

    public void setTransferedDate(Date transferedDate) {
        this.transferedDate = transferedDate;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
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
