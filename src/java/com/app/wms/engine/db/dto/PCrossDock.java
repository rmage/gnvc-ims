package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class PCrossDock {
    
    private String PCode;
    
    private String SOCode;
    
    private String TallymanCode;
    
    private String WHCode;
    
    private Date PDate;
    
    private String ApprovedBy;
    
    private Date ApprovedDate;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getPCode() {
        return PCode;
    }

    public String getSOCode() {
        return SOCode;
    }

    public String getTallymanCode() {
        return TallymanCode;
    }

    public String getWHCode() {
        return WHCode;
    }

    public Date getPDate() {
        return PDate;
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

    public void setPCode(String PCode) {
        this.PCode = PCode;
    }

    public void setSOCode(String SOCode) {
        this.SOCode = SOCode;
    }

    public void setTallymanCode(String TallymanCode) {
        this.TallymanCode = TallymanCode;
    }

    public void setWHCode(String WHCode) {
        this.WHCode = WHCode;
    }

    public void setPDate(Date PDate) {
        this.PDate = PDate;
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
    
    

}
