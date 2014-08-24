package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class FishStockCardAccounting implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String wsINNo;
    private String wsINType;
    private Date wsINDate;
    private Double wsINQuantity;
    private String wsOUTNo;
    private String wsOUTType;
    private Date wsOUTDate;
    private String wlscOUTNo;
    private Date wlscOUTDate;
    private Double wsOUTQuantity;
    private Double quantityBalance;
    private String currencyCode;
    private Double amount;
    private Double rateIDR;
    private Double amountIDR;
    private Double totalINIDR;
    private Double totalOUTIDR;
    private Double balanceIDR;
    private String createdBy;
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWsINNo() {
        return wsINNo;
    }

    public void setWsINNo(String wsINNo) {
        this.wsINNo = wsINNo;
    }

    public String getWsINType() {
        return wsINType;
    }

    public void setWsINType(String wsINType) {
        this.wsINType = wsINType;
    }

    public Date getWsINDate() {
        return wsINDate;
    }

    public void setWsINDate(Date wsINDate) {
        this.wsINDate = wsINDate;
    }

    public Double getWsINQuantity() {
        return wsINQuantity;
    }

    public void setWsINQuantity(Double wsINQuantity) {
        this.wsINQuantity = wsINQuantity;
    }

    public String getWsOUTNo() {
        return wsOUTNo;
    }

    public void setWsOUTNo(String wsOUTNo) {
        this.wsOUTNo = wsOUTNo;
    }

    public String getWsOUTType() {
        return wsOUTType;
    }

    public void setWsOUTType(String wsOUTType) {
        this.wsOUTType = wsOUTType;
    }

    public Date getWsOUTDate() {
        return wsOUTDate;
    }

    public void setWsOUTDate(Date wsOUTDate) {
        this.wsOUTDate = wsOUTDate;
    }

    public String getWlscOUTNo() {
        return wlscOUTNo;
    }

    public void setWlscOUTNo(String wlscOUTNo) {
        this.wlscOUTNo = wlscOUTNo;
    }

    public Date getWlscOUTDate() {
        return wlscOUTDate;
    }

    public void setWlscOUTDate(Date wlscOUTDate) {
        this.wlscOUTDate = wlscOUTDate;
    }

    public Double getWsOUTQuantity() {
        return wsOUTQuantity;
    }

    public void setWsOUTQuantity(Double wsOUTQuantity) {
        this.wsOUTQuantity = wsOUTQuantity;
    }

    public Double getQuantityBalance() {
        return quantityBalance;
    }

    public void setQuantityBalance(Double quantityBalance) {
        this.quantityBalance = quantityBalance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRateIDR() {
        return rateIDR;
    }

    public void setRateIDR(Double rateIDR) {
        this.rateIDR = rateIDR;
    }

    public Double getAmountIDR() {
        return amountIDR;
    }

    public void setAmountIDR(Double amountIDR) {
        this.amountIDR = amountIDR;
    }

    public Double getTotalINIDR() {
        return totalINIDR;
    }

    public void setTotalINIDR(Double totalINIDR) {
        this.totalINIDR = totalINIDR;
    }

    public Double getTotalOUTIDR() {
        return totalOUTIDR;
    }

    public void setTotalOUTIDR(Double totalOUTIDR) {
        this.totalOUTIDR = totalOUTIDR;
    }

    public Double getBalanceIDR() {
        return balanceIDR;
    }

    public void setBalanceIDR(Double balanceIDR) {
        this.balanceIDR = balanceIDR;
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

}
