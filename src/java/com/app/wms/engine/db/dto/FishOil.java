package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishOil implements Serializable{
    private int foId;
    private String foMonth;
    private String foYear;
    private String notedBy;
    private Date NotedDate;
    private String approvedBy;
    private Date approvedDate;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

    public int getFoId() {
        return foId;
    }

    public void setFoId(int foId) {
        this.foId = foId;
    }

    public String getFoMonth() {
        return foMonth;
    }

    public void setFoMonth(String foMonth) {
        this.foMonth = foMonth;
    }

    public String getFoYear() {
        return foYear;
    }

    public void setFoYear(String foYear) {
        this.foYear = foYear;
    }

    public String getNotedBy() {
        return notedBy;
    }

    public void setNotedBy(String notedBy) {
        this.notedBy = notedBy;
    }

    public Date getNotedDate() {
        return NotedDate;
    }

    public void setNotedDate(Date NotedDate) {
        this.NotedDate = NotedDate;
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
