package com.spfi.ims.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FishReclassificationDetail implements Serializable {
    
    private int id;
    
    private int frId;
    
    private int fromVesselId;
    
    private int fromFishId;
    
    private int fromStorageId;
    
    private BigDecimal fromQty;
    
    private int toVesselId;
    
    private int toFishId;
    
    private int toStorageId;
    
    private BigDecimal toQty;
    
    private String remarks;
    
    private String frType;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.spfi.ims.dto.FishReclassificationDetail: " );
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrId() {
        return frId;
    }

    public void setFrId(int frId) {
        this.frId = frId;
    }

    public int getFromVesselId() {
        return fromVesselId;
    }

    public void setFromVesselId(int fromVesselId) {
        this.fromVesselId = fromVesselId;
    }

    public int getFromFishId() {
        return fromFishId;
    }

    public void setFromFishId(int fromFishId) {
        this.fromFishId = fromFishId;
    }

    public int getFromStorageId() {
        return fromStorageId;
    }

    public void setFromStorageId(int fromStorageId) {
        this.fromStorageId = fromStorageId;
    }

    public BigDecimal getFromQty() {
        return fromQty;
    }

    public void setFromQty(BigDecimal fromQty) {
        this.fromQty = fromQty;
    }

    public int getToVesselId() {
        return toVesselId;
    }

    public void setToVesselId(int toVesselId) {
        this.toVesselId = toVesselId;
    }

    public int getToFishId() {
        return toFishId;
    }

    public void setToFishId(int toFishId) {
        this.toFishId = toFishId;
    }

    public int getToStorageId() {
        return toStorageId;
    }

    public void setToStorageId(int toStorageId) {
        this.toStorageId = toStorageId;
    }

    public BigDecimal getToQty() {
        return toQty;
    }

    public void setToQty(BigDecimal toQty) {
        this.toQty = toQty;
    }

    public String getFrType() {
        return frType;
    }

    public void setFrType(String frType) {
        this.frType = frType;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
}
