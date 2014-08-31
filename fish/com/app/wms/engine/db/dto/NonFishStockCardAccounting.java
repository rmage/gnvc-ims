package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NonFishStockCardAccounting implements Serializable, Comparable<NonFishStockCardAccounting> {

    private static final long serialVersionUID = 1L;
    private int id;
    private Integer productId;
    /*DOCUMENT*/
    private Date stockCardDate;
    private String code;
    private String number;
    /*UNIT COST*/
    private String currencyCode;
    private BigDecimal rateValue;
    private BigDecimal amount;
    private BigDecimal amountIDR;
    /*IN*/
    private Double quantityIN;
    private BigDecimal unitCostIN;
    private BigDecimal amountIN;
    /*OUT*/
    private Double quantityOUT;
    private BigDecimal unitCostOUT;
    private BigDecimal amountOUT;
    /*ENDING BALANCE*/
    private Double quantityEND;
    private BigDecimal unitCostEND;
    private BigDecimal amountEND;

    public NonFishStockCardAccounting() {
        this.id = 0;
        this.productId = 0;
        this.stockCardDate = new Date();
        this.code = "";
        this.number = "";
        this.currencyCode = "";
        this.rateValue = BigDecimal.ZERO;
        this.amount = BigDecimal.ZERO;
        this.amountIDR = BigDecimal.ZERO;
        this.quantityIN = 0d;
        this.unitCostIN = BigDecimal.ZERO;
        this.amountIN = BigDecimal.ZERO;
        this.quantityOUT = 0d;
        this.unitCostOUT = BigDecimal.ZERO;
        this.amountOUT = BigDecimal.ZERO;
        this.quantityEND = 0d;
        this.unitCostEND = BigDecimal.ZERO;
        this.amountEND = BigDecimal.ZERO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getStockCardDate() {
        return stockCardDate;
    }

    public void setStockCardDate(Date stockCardDate) {
        this.stockCardDate = stockCardDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountIDR() {
        return amountIDR;
    }

    public void setAmountIDR(BigDecimal amountIDR) {
        this.amountIDR = amountIDR;
    }

    public Double getQuantityIN() {
        return quantityIN;
    }

    public void setQuantityIN(Double quantityIN) {
        this.quantityIN = quantityIN;
    }

    public BigDecimal getUnitCostIN() {
        return unitCostIN;
    }

    public void setUnitCostIN(BigDecimal unitCostIN) {
        this.unitCostIN = unitCostIN;
    }

    public BigDecimal getAmountIN() {
        return amountIN;
    }

    public void setAmountIN(BigDecimal amountIN) {
        this.amountIN = amountIN;
    }

    public Double getQuantityOUT() {
        return quantityOUT;
    }

    public void setQuantityOUT(Double quantityOUT) {
        this.quantityOUT = quantityOUT;
    }

    public BigDecimal getUnitCostOUT() {
        return unitCostOUT;
    }

    public void setUnitCostOUT(BigDecimal unitCostOUT) {
        this.unitCostOUT = unitCostOUT;
    }

    public BigDecimal getAmountOUT() {
        return amountOUT;
    }

    public void setAmountOUT(BigDecimal amountOUT) {
        this.amountOUT = amountOUT;
    }

    public Double getQuantityEND() {
        return quantityEND;
    }

    public void setQuantityEND(Double quantityEND) {
        this.quantityEND = quantityEND;
    }

    public BigDecimal getUnitCostEND() {
        return unitCostEND;
    }

    public void setUnitCostEND(BigDecimal unitCostEND) {
        this.unitCostEND = unitCostEND;
    }

    public BigDecimal getAmountEND() {
        return amountEND;
    }

    public void setAmountEND(BigDecimal amountEND) {
        this.amountEND = amountEND;
    }

    public int compareTo(NonFishStockCardAccounting o) {
        return getStockCardDate().compareTo(o.getStockCardDate());
    }

}
