package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class PACrossDock {
    
    private String PACode;
    
    private String GRCode;
    
    private String LocationCode;
    
    private String WHCode;
    
    private String PAInfo;
    
    private String TallymanCode;
    
    private String ApprovedBy;
    
    private Date ApprovedDate;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getPACode() {
        return PACode;
    }

    public String getGRCode() {
        return GRCode;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public String getWHCode() {
        return WHCode;
    }

    public String getPAInfo() {
        return PAInfo;
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

    public void setPACode(String PACode) {
        this.PACode = PACode;
    }

    public void setGRCode(String GRCode) {
        this.GRCode = GRCode;
    }

    public void setLocationCode(String LocationCode) {
        this.LocationCode = LocationCode;
    }

    public void setWHCode(String WHCode) {
        this.WHCode = WHCode;
    }

    public void setPAInfo(String PAInfo) {
        this.PAInfo = PAInfo;
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

    public String getTallymanCode() {
        return TallymanCode;
    }

    public void setTallymanCode(String TallymanCode) {
        this.TallymanCode = TallymanCode;
    }
    
}
