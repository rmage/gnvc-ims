package com.app.wms.engine.db.dto;

import java.util.Date;

/* 
 * created by FYA
 * febryan.dreamer@gmail.com
 * 10 February 2013
 */

public class Quarantine {

    private String QCode;
    
    private Integer POCode;
    
    private String putawayCode;
    
    private String whCode;
    
    private Date QDate;
    
    private String CreatedBy;
    
    private Date CreatedDate;
    
    private String UpdatedBy;
    
    private Date UpdatedDate;

    public String getQCode() {
        return QCode;
    }

    public String getPutawayCode() {
        return putawayCode;
    }

    public String getWhCode() {
        return whCode;
    }

    public Date getQDate() {
        return QDate;
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

    public void setQCode(String QCode) {
        this.QCode = QCode;
    }

    public void setPutawayCode(String putawayCode) {
        this.putawayCode = putawayCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public void setQDate(Date QDate) {
        this.QDate = QDate;
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

    public Integer getPOCode() {
        return POCode;
    }

    public void setPOCode(Integer POCode) {
        this.POCode = POCode;
    }
    
}
