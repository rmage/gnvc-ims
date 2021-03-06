package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Ts implements Serializable {
    
    private String tsCode;
    
    private Date tsDate;
    
    private String tsInfo;
    
    private String tsTo;
    
    private String tsModule;
    
    private String tsType;
    
    private String swsCode;
    
    private String notedBy;
    
    private Date notedDate;
    
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
        sb.append( "com.app.wms.engine.db.dto.Ts: " );
        sb.append("tsCode = ").append( getTsCode() );
        sb.append(", tsDate = ").append( getTsDate() );
        sb.append(", tsInfo = ").append( getTsInfo() );
        sb.append(", swsCode = ").append( getSwsCode() );
        sb.append(", notedBy = ").append( getNotedBy() );
        sb.append(", notedDate = ").append( getNotedDate() );
        sb.append(", approvedBy = ").append( getApprovedBy() );
        sb.append(", approvedDate = ").append( getApprovedDate() );
        sb.append(", receiveBy = ").append( getReceivedBy() );
        sb.append(", receiveDate = ").append( getReceivedDate() );
        sb.append(", createdBy = ").append( getCreatedBy() );
        sb.append(", createdDate = ").append( getCreatedDate() );
        sb.append(", updatedBy = ").append( getUpdatedBy() );
        sb.append(", updatedDate = ").append( getUpdatedDate() );
        return sb.toString();
    }

    public String getTsCode() {
        return tsCode;
    }

    public void setTsCode(String tsCode) {
        this.tsCode = tsCode;
    }

    public Date getTsDate() {
        return tsDate;
    }

    public void setTsDate(Date tsDate) {
        this.tsDate = tsDate;
    }

    public String getTsInfo() {
        return tsInfo;
    }

    public void setTsInfo(String tsInfo) {
        this.tsInfo = tsInfo;
    }

    public String getSwsCode() {
        return swsCode;
    }

    public void setSwsCode(String swsCode) {
        this.swsCode = swsCode;
    }

    public String getNotedBy() {
        return notedBy;
    }

    public void setNotedBy(String notedBy) {
        this.notedBy = notedBy;
    }

    public Date getNotedDate() {
        return notedDate;
    }

    public void setNotedDate(Date notedDate) {
        this.notedDate = notedDate;
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

    public String getTsTo() {
        return tsTo;
    }

    public void setTsTo(String tsTo) {
        this.tsTo = tsTo;
    }

    public String getTsModule() {
        return tsModule;
    }

    public void setTsModule(String tsModule) {
        this.tsModule = tsModule;
    }

    public String getTsType() {
        return tsType;
    }

    public void setTsType(String tsType) {
        this.tsType = tsType;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }   

}
