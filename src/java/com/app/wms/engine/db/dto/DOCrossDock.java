package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class DOCrossDock {
    
    private String DOCode;
    
    private String SOCode;
    
    private String WHCode;
    
    private String DOName;
    
    private Date DODate;
    
    private String ApprovedBy;
    
    private Date ApprovedDate;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getDOCode() {
        return DOCode;
    }

    public String getSOCode() {
        return SOCode;
    }

    public String getWHCode() {
        return WHCode;
    }

    public Date getDODate() {
        return DODate;
    }

    public String getApprovedBy() {
        return ApprovedBy;
    }

    public Date getApprovedDate() {
        return ApprovedDate;
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

    public void setDOCode(String DOCode) {
        this.DOCode = DOCode;
    }

    public void setSOCode(String SOCode) {
        this.SOCode = SOCode;
    }

    public void setWHCode(String WHCode) {
        this.WHCode = WHCode;
    }

    public void setDODate(Date DODate) {
        this.DODate = DODate;
    }

    public void setApprovedBy(String ApprovedBy) {
        this.ApprovedBy = ApprovedBy;
    }

    public void setApprovedDate(Date ApprovedDate) {
        this.ApprovedDate = ApprovedDate;
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

    public String getDOName() {
        return DOName;
    }

    public void setDOName(String DOName) {
        this.DOName = DOName;
    }

}
