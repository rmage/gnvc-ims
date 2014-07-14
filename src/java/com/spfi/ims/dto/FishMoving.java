package com.spfi.ims.dto;

import java.io.Serializable;
import java.util.Date;

public class FishMoving implements Serializable {
    
    private int id;
    
    private String code;
    
    private Date date;
    
    private int fromStorageId;
    
    private int toStorageId;
    
    private String remarks;
    
    private String issuedBy;
    
    private Date issuedDate;
    
    private String notedby;
    
    private Date notedBy;
    
    private String approvedBy;
    
    private Date approvedDate;
    
    private String receivedBy;
    
    private Date receivedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.spfi.ims.dto.FishMoving: " );
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFromStorageId() {
        return fromStorageId;
    }

    public void setFromStorageId(int fromStorageId) {
        this.fromStorageId = fromStorageId;
    }

    public int getToStorageId() {
        return toStorageId;
    }

    public void setToStorageId(int toStorageId) {
        this.toStorageId = toStorageId;
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

    public String getNotedby() {
        return notedby;
    }

    public void setNotedby(String notedby) {
        this.notedby = notedby;
    }

    public Date getNotedBy() {
        return notedBy;
    }

    public void setNotedBy(Date notedBy) {
        this.notedBy = notedBy;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
}
