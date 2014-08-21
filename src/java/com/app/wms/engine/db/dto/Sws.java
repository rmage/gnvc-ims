package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Sws implements Serializable {
    
    private String swsCode;
    
    private Date swsDate;
    
    private String swsInfo;
    
    private String departmentCode;
    
    private String approvedBy;
    
    private Date approvedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.Sws: " );
        sb.append("swsCode = ").append( getSwsCode() );
        sb.append(", swsDate = ").append( getSwsDate() );
        sb.append(", swsInfo = ").append( getSwsInfo() );
        sb.append(", departmentCode = ").append( getDepartmentCode() );
        sb.append(", approvedBy = ").append( getApprovedBy() );
        sb.append(", approvedDate = ").append( getApprovedDate() );
        sb.append(", createdBy = ").append( getCreatedBy() );
        sb.append(", createdDate = ").append( getCreatedDate() );
        sb.append(", updatedBy = ").append( getUpdatedBy() );
        sb.append(", updatedDate = ").append( getUpdatedDate() );
        return sb.toString();
    }

    public String getSwsCode() {
        return swsCode;
    }

    public void setSwsCode(String swsCode) {
        this.swsCode = swsCode;
    }

    public Date getSwsDate() {
        return swsDate;
    }

    public void setSwsDate(Date swsDate) {
        this.swsDate = swsDate;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSwsInfo() {
        return swsInfo;
    }

    public void setSwsInfo(String swsInfo) {
        this.swsInfo = swsInfo;
    }
    
}
