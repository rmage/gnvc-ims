/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Faridzi
 */
public class FGStockCardAccounting implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer itemId;
    private String itemName;
    private Double endQuantity;
    private Double fcls;
    private Date scDate;
    /*DEFAULT IN USD*/
    private BigDecimal varCost;
    private BigDecimal fixCost;
    private BigDecimal totalCost;
    /*IN IDR*/
    private BigDecimal amountVarCost;
    private BigDecimal amountFixCost;
    private BigDecimal amountTotalCost;

    /*CURRENCY*/
    private Date rateDate;
    private BigDecimal rateValue;

    /*HELP VAR*/
    private String currencyCodeFrom;

    private FGUnitCost fgUnitCost;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getEndQuantity() {
        return endQuantity;
    }

    public void setEndQuantity(Double endQuantity) {
        this.endQuantity = endQuantity;
    }

    public Double getFcls() {
        return fcls;
    }

    public void setFcls(Double fcls) {
        this.fcls = fcls;
    }

    public Date getScDate() {
        return scDate;
    }

    public void setScDate(Date scDate) {
        this.scDate = scDate;
    }

    public BigDecimal getVarCost() {
        return varCost;
    }

    public void setVarCost(BigDecimal varCost) {
        this.varCost = varCost;
    }

    public BigDecimal getFixCost() {
        return fixCost;
    }

    public void setFixCost(BigDecimal fixCost) {
        this.fixCost = fixCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getAmountVarCost() {
        return amountVarCost;
    }

    public void setAmountVarCost(BigDecimal amountVarCost) {
        this.amountVarCost = amountVarCost;
    }

    public BigDecimal getAmountFixCost() {
        return amountFixCost;
    }

    public void setAmountFixCost(BigDecimal amountFixCost) {
        this.amountFixCost = amountFixCost;
    }

    public BigDecimal getAmountTotalCost() {
        return amountTotalCost;
    }

    public void setAmountTotalCost(BigDecimal amountTotalCost) {
        this.amountTotalCost = amountTotalCost;
    }

    public FGUnitCost getFgUnitCost() {
        return fgUnitCost;
    }

    public void setFgUnitCost(FGUnitCost fgUnitCost) {
        this.fgUnitCost = fgUnitCost;
    }

    public String getCurrencyCodeFrom() {
        return currencyCodeFrom;
    }

    public void setCurrencyCodeFrom(String currencyCodeFrom) {
        this.currencyCodeFrom = currencyCodeFrom;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public BigDecimal getRateValue() {
        return rateValue;
    }

    public void setRateValue(BigDecimal rateValue) {
        this.rateValue = rateValue;
    }

}
