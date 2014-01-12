package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class SOCrossDock {
    
    private String SOCode;
    
    private String POCode;
    
    private String ConsigneeCode;
    
    private String WHCode;
    
    private Date SODate;
    
    private String ApprovedBy;
    
    private Date ApprovedDate;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getSOCode() {
        return SOCode;
    }

    public String getPOCode() {
        return POCode;
    }

    public String getConsigneeCode() {
        return ConsigneeCode;
    }

    public Date getSODate() {
        return SODate;
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

    public void setSOCode(String SOCode) {
        this.SOCode = SOCode;
    }

    public void setPOCode(String POCode) {
        this.POCode = POCode;
    }

    public void setConsigneeCode(String ConsigneeCode) {
        this.ConsigneeCode = ConsigneeCode;
    }

    public void setSODate(Date SODate) {
        this.SODate = SODate;
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

}
