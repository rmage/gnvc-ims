package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 */

public class POCrossDockDtl {
    
    private String POCodeDtl;
    
    private Integer POCode;
    
    private String ProductCode;
    
    private Integer POQty;
    
    private String POType;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getPOCodeDtl() {
        return POCodeDtl;
    }

    public Integer getPOCode() {
        return POCode;
    }

    public Integer getPOQty() {
        return POQty;
    }

    public String getPOType() {
        return POType;
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

    public void setPOCodeDtl(String POCodeDtl) {
        this.POCodeDtl = POCodeDtl;
    }

    public void setPOCode(Integer POCode) {
        this.POCode = POCode;
    }

    public void setPOQty(Integer POQty) {
        this.POQty = POQty;
    }

    public void setPOType(String POType) {
        this.POType = POType;
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

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String ProductCode) {
        this.ProductCode = ProductCode;
    }
    
}
