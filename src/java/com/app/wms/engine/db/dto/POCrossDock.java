package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class POCrossDock {

    private Integer POCode;
    
    private String PONumber;
    
    private String WHCode;
    
    private Date PODate;
    
    private String ApprovedBy;
    
    private Date ApprovedDate;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public Integer getPOCode() {
        return POCode;
    }

    public Date getPODate() {
        return PODate;
    }

    public String getWHCode() {
        return WHCode;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setPOCode(Integer POCode) {
        this.POCode = POCode;
    }

    public void setPODate(Date PODate) {
        this.PODate = PODate;
    }

    public void setWHCode(String WHCode) {
        this.WHCode = WHCode;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public void setUpdatedBy(String UpdatedBy) {
        this.UpdatedBy = UpdatedBy;
    }

    public void setUpdatedDate(Date UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }

    public String getApprovedBy() {
        return ApprovedBy;
    }

    public Date getApprovedDate() {
        return ApprovedDate;
    }

    public void setApprovedBy(String ApprovedBy) {
        this.ApprovedBy = ApprovedBy;
    }

    public void setApprovedDate(Date ApprovedDate) {
        this.ApprovedDate = ApprovedDate;
    }

    public String getPONumber() {
        return PONumber;
    }

    public void setPONumber(String PONumber) {
        this.PONumber = PONumber;
    }
    
}
