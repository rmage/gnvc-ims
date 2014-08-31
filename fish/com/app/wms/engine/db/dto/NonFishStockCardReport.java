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
public class NonFishStockCardReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    protected Integer id;
    protected String itemCode;
    protected String itemDescription;
    protected String unit;
    protected Double qty;
    protected BigDecimal unitCost;
    protected BigDecimal amountToDate;
    protected BigDecimal beginningAmount;
    protected BigDecimal transactionAmount;
    protected Date createdDate;
    protected String createdBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getAmountToDate() {
        return amountToDate;
    }

    public void setAmountToDate(BigDecimal amountToDate) {
        this.amountToDate = amountToDate;
    }

    public BigDecimal getBeginningAmount() {
        return beginningAmount;
    }

    public void setBeginningAmount(BigDecimal beginningAmount) {
        this.beginningAmount = beginningAmount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
        
}
