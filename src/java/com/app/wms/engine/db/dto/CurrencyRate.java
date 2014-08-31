package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CurrencyRate  implements Serializable {
    
    private int rateId;
    
    private String currencyCode;
    
    private BigDecimal rateValue;
    
    private Date rateDate;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String rateDateString;
    
    @Override
    public String toString() {
        //FIXME :: CurrencyRate @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.CurrencyRate: " );
        
        return sb.toString();
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
    
       
}
