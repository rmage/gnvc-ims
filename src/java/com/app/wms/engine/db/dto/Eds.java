package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Eds implements Serializable {

    private int edsCode;
    
    private Date edsDate;
    
    private String edsRemarks;
    
    private String borCode;
    
    private String edsVan;
    
    private String edsSeal;
    
    private String edsVessel;
    
    private String edsPlatNo;
    
    private String edsTimeIn;
    
    private String edsTimeOut;
    
    private String edsDriver;
    
    private String edsCi;
    
    private String issuedBy;
    
    private Date issuedDate;
    
    private String checkedBy;
    
    private Date checkedDate;
    
    private String approvedBy;
    
    private Date approvedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        //FIXME :: Eds @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.Eds: " );
        
        return sb.toString();
    }

    public int getEdsCode() {
        return edsCode;
    }

    public void setEdsCode(int edsCode) {
        this.edsCode = edsCode;
    }

    public Date getEdsDate() {
        return edsDate;
    }

    public void setEdsDate(Date edsDate) {
        this.edsDate = edsDate;
    }

    public String getEdsRemarks() {
        return edsRemarks;
    }

    public void setEdsRemarks(String edsRemarks) {
        this.edsRemarks = edsRemarks;
    }

    public String getBorCode() {
        return borCode;
    }

    public void setBorCode(String borCode) {
        this.borCode = borCode;
    }

    public String getEdsVan() {
        return edsVan;
    }

    public void setEdsVan(String edsVan) {
        this.edsVan = edsVan;
    }

    public String getEdsSeal() {
        return edsSeal;
    }

    public void setEdsSeal(String edsSeal) {
        this.edsSeal = edsSeal;
    }

    public String getEdsVessel() {
        return edsVessel;
    }

    public void setEdsVessel(String edsVessel) {
        this.edsVessel = edsVessel;
    }

    public String getEdsPlatNo() {
        return edsPlatNo;
    }

    public void setEdsPlatNo(String edsPlatNo) {
        this.edsPlatNo = edsPlatNo;
    }

    public String getEdsTimeIn() {
        return edsTimeIn;
    }

    public void setEdsTimeIn(String edsTimeIn) {
        this.edsTimeIn = edsTimeIn;
    }

    public String getEdsTimeOut() {
        return edsTimeOut;
    }

    public void setEdsTimeOut(String edsTimeOut) {
        this.edsTimeOut = edsTimeOut;
    }

    public String getEdsDriver() {
        return edsDriver;
    }

    public void setEdsDriver(String edsDriver) {
        this.edsDriver = edsDriver;
    }

    public String getEdsCi() {
        return edsCi;
    }

    public void setEdsCi(String edsCi) {
        this.edsCi = edsCi;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public Date getCheckedDate() {
        return checkedDate;
    }

    public void setCheckedDate(Date checkedDate) {
        this.checkedDate = checkedDate;
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

}
