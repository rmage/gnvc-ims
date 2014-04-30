package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Ofal implements Serializable {
    
    private int ofalId;
    
    private String ofalLabelNw;
    
    private String ofalLabelDw;
    
    private String borCode;
    
    private int borId;
    
    private String ofalShipment;
    
    private String preparedBy;
    
    private Date preparedDate;
    
    private String reviewedBy;
    
    private Date reviewedDate;
    
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
        //FIXME :: Ofal @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.Ofal: " );
        
        return sb.toString();
    }

    public int getOfalId() {
        return ofalId;
    }

    public void setOfalId(int ofalId) {
        this.ofalId = ofalId;
    }

    public String getOfalLabelNw() {
        return ofalLabelNw;
    }

    public void setOfalLabelNw(String ofalLabelNw) {
        this.ofalLabelNw = ofalLabelNw;
    }

    public String getOfalLabelDw() {
        return ofalLabelDw;
    }

    public void setOfalLabelDw(String ofalLabelDw) {
        this.ofalLabelDw = ofalLabelDw;
    }

    public String getBorCode() {
        return borCode;
    }

    public void setBorCode(String borCode) {
        this.borCode = borCode;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public Date getPreparedDate() {
        return preparedDate;
    }

    public void setPreparedDate(Date preparedDate) {
        this.preparedDate = preparedDate;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public Date getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(Date reviewedDate) {
        this.reviewedDate = reviewedDate;
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

    public String getOfalShipment() {
        return ofalShipment;
    }

    public void setOfalShipment(String ofalShipment) {
        this.ofalShipment = ofalShipment;
    }

    public int getBorId() {
        return borId;
    }

    public void setBorId(int borId) {
        this.borId = borId;
    }
    
}
