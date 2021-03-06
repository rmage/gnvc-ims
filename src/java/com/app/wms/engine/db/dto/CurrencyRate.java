package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CurrencyRate implements Serializable {

    private int rateId;

    private String currencyCodeFrom;

    private String currencyCodeTo;

    private BigDecimal rateValue;

    private Date rateDate;

    private String createdBy;

    private Date createdDate;

    private String rateDateString;

    private String currencyType;

    private Date weekStartDate;

    private Date weekEndDate;

    private Date monthDate;

    public CurrencyRate() {
        this.currencyCodeFrom = "";
        this.currencyCodeTo = "";
        this.rateId = 0;
        this.rateValue = BigDecimal.ZERO;
        this.rateDate = new Date();
        this.createdBy = "";
        this.createdDate = new Date();
        this.rateDateString = "";
        this.currencyType = "";
        this.weekStartDate = new Date();
        this.weekEndDate = new Date();
        this.monthDate = new Date();
    }

    @Override
    public String toString() {
        //FIXME :: CurrencyRate @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append("com.app.wms.engine.db.dto.CurrencyRate: ");

        return sb.toString();
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public String getCurrencyCodeFrom() {
        return currencyCodeFrom;
    }

    public void setcurrencyCodeFrom(String currencyCodeFrom) {
        this.currencyCodeFrom = currencyCodeFrom;
    }

    public BigDecimal getRateValue() {
        return rateValue;
    }

    public void setRateValue(BigDecimal rateValue) {
        this.rateValue = rateValue;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
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

    public String getRateDateString() {
        return rateDateString;
    }

    public void setRateDateString(String rateDateString) {
        this.rateDateString = rateDateString;
    }

    public String getCurrencyCodeTo() {
        return currencyCodeTo;
    }

    public void setCurrencyCodeTo(String currencyCodeTo) {
        this.currencyCodeTo = currencyCodeTo;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public Date getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(Date monthDate) {
        this.monthDate = monthDate;
    }

}
