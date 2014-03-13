package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishMeal implements Serializable {
    
    private int fmId;
    
    private String fmMonth;
    
    private String fmYear;
    
    private String notedBy;
    
    private Date notedDate;
    
    private String approvedBy;
    
    private Date approvedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        //FIXME :: FishMeal @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.FishMeal: " );
        
        return sb.toString();
    }

    public int getFmId() {
        return fmId;
    }

    public void setFmId(int fmId) {
        this.fmId = fmId;
    }

    public String getFmMonth() {
        return fmMonth;
    }

    public void setFmMonth(String fmMonth) {
        this.fmMonth = fmMonth;
    }

    public String getFmYear() {
        return fmYear;
    }

    public void setFmYear(String fmYear) {
        this.fmYear = fmYear;
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
