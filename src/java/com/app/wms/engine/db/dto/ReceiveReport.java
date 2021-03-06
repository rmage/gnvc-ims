package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class ReceiveReport implements Serializable {

    private String rrCode;

    private Date rrDate;

    private int poCode;

    private String rrFrom;
    
    private String rrRemarks;

    private String evaluatedBy;

    private Date evaluatedDate;

    private String approvedBy;

    private Date approvedDate;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    private Double qty;

    private Double unitCost;

    private Po po;

    private CurrencyRate currencyRate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.app.wms.engine.db.dto.ReceiveReport: ");
        sb.append("rrCode = ").append(getRrCode());
        sb.append(", rrDate = ").append(getRrDate());
        sb.append(", poCode = ").append(getPoCode());
        sb.append(", rrFrom = ").append(getRrFrom());
        sb.append(", evaluatedBy = ").append(getEvaluatedBy());
        sb.append(", evaluatedDate = ").append(getEvaluatedDate());
        sb.append(", approvedBy = ").append(getApprovedBy());
        sb.append(", approvedDate = ").append(getApprovedDate());
        sb.append(", createdBy = ").append(getCreatedBy());
        sb.append(", createDate = ").append(getCreatedDate());
        sb.append(", updatedBy = ").append(getUpdatedBy());
        sb.append(", updatedDate = ").append(getUpdatedDate());
        return sb.toString();
    }

    public String getRrCode() {
        return rrCode;
    }

    public void setRrCode(String rrCode) {
        this.rrCode = rrCode;
    }

    public Date getRrDate() {
        return rrDate;
    }

    public void setRrDate(Date rrDate) {
        this.rrDate = rrDate;
    }

    public int getPoCode() {
        return poCode;
    }

    public void setPoCode(int poCode) {
        this.poCode = poCode;
    }

    public String getRrFrom() {
        return rrFrom;
    }

    public void setRrFrom(String rrFrom) {
        this.rrFrom = rrFrom;
    }

    public String getEvaluatedBy() {
        return evaluatedBy;
    }

    public void setEvaluatedBy(String evaluatedBy) {
        this.evaluatedBy = evaluatedBy;
    }

    public Date getEvaluatedDate() {
        return evaluatedDate;
    }

    public void setEvaluatedDate(Date evaluatedDate) {
        this.evaluatedDate = evaluatedDate;
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

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Po getPo() {
        return po;
    }

    public void setPo(Po po) {
        this.po = po;
    }

    public CurrencyRate getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(CurrencyRate currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getRrRemarks() {
        return rrRemarks;
    }

    public void setRrRemarks(String rrRemarks) {
        this.rrRemarks = rrRemarks;
    }

}
