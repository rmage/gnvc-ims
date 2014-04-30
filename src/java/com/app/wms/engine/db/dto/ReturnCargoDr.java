package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class ReturnCargoDr implements Serializable {
    
    private String rrCode;
    
    private Date rrDate;

    private String drCode;
    
    private String rrFrom;
    
    private String receivedBy;
    
    private Date receivedDate;
    
    private String evaluatedBy;
    
    private Date evaluatedDate;
    
    private String approvedBy;
    
    private Date approvedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        //FIXME :: ReturnCargoDr @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.ReturnCargoDr: " );
        
        return sb.toString();
    }

    public String getRrCode() {
        return rrCode;
    }

    public Date getRrDate() {
        return rrDate;
    }

    public String getDrCode() {
        return drCode;
    }

    public String getRrFrom() {
        return rrFrom;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public String getEvaluatedBy() {
        return evaluatedBy;
    }

    public Date getEvaluatedDate() {
        return evaluatedDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setRrCode(String rrCode) {
        this.rrCode = rrCode;
    }

    public void setRrDate(Date rrDate) {
        this.rrDate = rrDate;
    }

    public void setDrCode(String drCode) {
        this.drCode = drCode;
    }

    public void setRrFrom(String rrFrom) {
        this.rrFrom = rrFrom;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setEvaluatedBy(String evaluatedBy) {
        this.evaluatedBy = evaluatedBy;
    }

    public void setEvaluatedDate(Date evaluatedDate) {
        this.evaluatedDate = evaluatedDate;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
}
