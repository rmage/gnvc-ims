package com.app.wms.engine.db.dto;

import java.util.Date;

public class CrossDock {

    private String CDCode;
    
    private String PACode;
    
    private String PCode;
    
    private String WHCode;
    
    private Date CDDateIn;
    
    private Date CDDateOut;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getCDCode() {
        return CDCode;
    }

    public String getPACode() {
        return PACode;
    }

    public String getPCode() {
        return PCode;
    }

    public String getWHCode() {
        return WHCode;
    }

    public Date getCDDateIn() {
        return CDDateIn;
    }

    public Date getCDDateOut() {
        return CDDateOut;
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

    public void setCDCode(String CDCode) {
        this.CDCode = CDCode;
    }

    public void setPACode(String PACode) {
        this.PACode = PACode;
    }

    public void setPCode(String PCode) {
        this.PCode = PCode;
    }

    public void setWHCode(String WHCode) {
        this.WHCode = WHCode;
    }

    public void setCDDateIn(Date CDDateIn) {
        this.CDDateIn = CDDateIn;
    }

    public void setCDDateOut(Date CDDateOut) {
        this.CDDateOut = CDDateOut;
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
