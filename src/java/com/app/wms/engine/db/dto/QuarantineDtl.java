package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 11 February 2013
 */

public class QuarantineDtl {

    private String QCodeDtl;
    
    private String QCode;
    
    private String productCode;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getQCodeDtl() {
        return QCodeDtl;
    }

    public String getQCode() {
        return QCode;
    }

    public String getProductCode() {
        return productCode;
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

    public void setQCodeDtl(String QCodeDtl) {
        this.QCodeDtl = QCodeDtl;
    }

    public void setQCode(String QCode) {
        this.QCode = QCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
