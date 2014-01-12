package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class GRCrossDockDtl {

    private String GRCodeDtl;
    
    private String GRCode;
    
    private String POCodeDtl;
    
    private String GRStatus;
    
    private String CreatedBy;
    
    private Date CreatedDate;

    private String UpdatedBy;

    private Date UpdatedDate;

    public String getGRCodeDtl() {
        return GRCodeDtl;
    }

    public String getGRCode() {
        return GRCode;
    }

    public String getPOCodeDtl() {
        return POCodeDtl;
    }

    public String getGRStatus() {
        return GRStatus;
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

    public void setGRCodeDtl(String GRCodeDtl) {
        this.GRCodeDtl = GRCodeDtl;
    }

    public void setGRCode(String GRCode) {
        this.GRCode = GRCode;
    }

    public void setPOCodeDtl(String POCodeDtl) {
        this.POCodeDtl = POCodeDtl;
    }

    public void setGRStatus(String GRStatus) {
        this.GRStatus = GRStatus;
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
