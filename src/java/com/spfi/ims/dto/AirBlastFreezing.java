package com.spfi.ims.dto;

import java.io.Serializable;
import java.util.Date;

public class AirBlastFreezing implements Serializable {
    
    private int id;
    
    private String abfNo;
    
    private Date abfDate;
    
    private int wsId;
    
    private int storageId;
    
    private String batchNo;
    
    private String regu;
    
    private String timeShift;
    
    private String timeStart;
    
    private String timeFinish;
    
    private String weightBy;
    
    private Date weightDate;
    
    private String checkedBy;
    
    private Date checkedDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    private String isCancel;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.spfi.ims.dto.AirBlastFreezing: " );
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbfNo() {
        return abfNo;
    }

    public void setAbfNo(String abfNo) {
        this.abfNo = abfNo;
    }

    public Date getAbfDate() {
        return abfDate;
    }

    public void setAbfDate(Date abfDate) {
        this.abfDate = abfDate;
    }

    public int getWsId() {
        return wsId;
    }

    public void setWsId(int wsId) {
        this.wsId = wsId;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getRegu() {
        return regu;
    }

    public void setRegu(String regu) {
        this.regu = regu;
    }

    public String getTimeShift() {
        return timeShift;
    }

    public void setTimeShift(String timeShift) {
        this.timeShift = timeShift;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(String timeFinish) {
        this.timeFinish = timeFinish;
    }

    public String getWeightBy() {
        return weightBy;
    }

    public void setWeightBy(String weightBy) {
        this.weightBy = weightBy;
    }

    public Date getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(Date weightDate) {
        this.weightDate = weightDate;
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

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }
    
}
