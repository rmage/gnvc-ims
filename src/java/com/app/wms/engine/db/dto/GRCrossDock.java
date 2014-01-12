package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class GRCrossDock {

    private String GRCode;
    
    private Integer POCode;
    
    private String WHCode;
    
    private Date GRDate;
    
    private String ApprovedBy;
    
    private Date ApprovedDate;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getGRCode() {
        return GRCode;
    }

    public Integer getPOCode() {
        return POCode;
    }

    public String getWHCode() {
        return WHCode;
    }

    public Date getGRDate() {
        return GRDate;
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

    public void setGRCode(String GRCode) {
        this.GRCode = GRCode;
    }

    public void setPOCode(Integer POCode) {
        this.POCode = POCode;
    }

    public void setWHCode(String WHCode) {
        this.WHCode = WHCode;
    }

    public void setGRDate(Date GRDate) {
        this.GRDate = GRDate;
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
    
}
