package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Dr implements Serializable {

    private int drCode;
    
    private Date drDate;
    
    private String drFrom;
    
    private String drFromLoc;
    
    private String drToLoc;
    
    private String drRemarks;
    
    private String drType;
    
    private String supplierCode;
    
    private String orCode;
    
    private String dmCode;
    
    private String deliveredBy;
    
    private Date deliveredDate;
    
    private String approvedBy;
    
    private Date approvedDate;
    
    private String receivedBy;
    
    private Date receivedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    private Double qty;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.Dr: " );
        sb.append("drCode = ").append( getDrCode() );
        sb.append(", drDate = ").append( getDrDate() );
        sb.append(", drFrom = ").append( getDrFrom() );
        sb.append(", drFromLoc = ").append( getDrFromLoc() );
        sb.append(", drToLoc = ").append( getDrToLoc() );
        sb.append(", drRemarks = ").append( getDrRemarks() );
        sb.append(", supplierCode = ").append( getSupplierCode() );
        sb.append(", orCode = ").append( getOrCode() );
        sb.append(", dmCode = ").append( getDmCode( ));
        sb.append(", deliveredBy = ").append( getDeliveredBy() );
        sb.append(", deliveredDate = ").append( getDeliveredDate() );
        sb.append(", approvedBy = ").append( getApprovedBy() );
        sb.append(", approvedDate = ").append( getApprovedDate() );
        sb.append(", receivedBy = ").append( getReceivedBy() );
        sb.append(", receivedDate = ").append( getReceivedDate() );
        sb.append(", createdBy = ").append( getCreatedBy() );
        sb.append(", createdDate = ").append( getCreatedDate() );
        sb.append(", updatedBy = ").append( getUpdatedBy() );
        sb.append(", updatedDate = ").append( getUpdatedDate() );
        return sb.toString();
    }

    public int getDrCode() {
        return drCode;
    }

    public void setDrCode(int drCode) {
        this.drCode = drCode;
    }

    public Date getDrDate() {
        return drDate;
    }

    public void setDrDate(Date drDate) {
        this.drDate = drDate;
    }

    public String getDrFrom() {
        return drFrom;
    }

    public void setDrFrom(String drFrom) {
        this.drFrom = drFrom;
    }

    public String getDrFromLoc() {
        return drFromLoc;
    }

    public void setDrFromLoc(String drFromLoc) {
        this.drFromLoc = drFromLoc;
    }

    public String getDrToLoc() {
        return drToLoc;
    }

    public void setDrToLoc(String drToLoc) {
        this.drToLoc = drToLoc;
    }

    public String getDrRemarks() {
        return drRemarks;
    }

    public void setDrRemarks(String drRemarks) {
        this.drRemarks = drRemarks;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getOrCode() {
        return orCode;
    }

    public void setOrCode(String orCode) {
        this.orCode = orCode;
    }

    public String getDmCode() {
        return dmCode;
    }

    public void setDmCode(String dmCode) {
        this.dmCode = dmCode;
    }

    public String getDeliveredBy() {
        return deliveredBy;
    }

    public void setDeliveredBy(String deliveredBy) {
        this.deliveredBy = deliveredBy;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
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

    public String getDrType() {
        return drType;
    }

    public void setDrType(String drType) {
        this.drType = drType;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }
    
}
