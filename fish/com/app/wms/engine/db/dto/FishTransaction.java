/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Faridzi
 */
public class FishTransaction {

    private Integer id;
    private Integer fishId;
    private Integer fishSupplierId;
    private Date transactionDate;
    private Double quantity;
    private BigDecimal unitCost;
    private BigDecimal unitCostConverted;
    private String currencyCode;
    private String docNumber;
    private String type;

    private Fish fish;

    private FishSupplier fishSupplier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFishId() {
        return fishId;
    }

    public void setFishId(Integer fishId) {
        this.fishId = fishId;
    }

    public Integer getFishSupplierId() {
        return fishSupplierId;
    }

    public void setFishSupplierId(Integer fishSupplierId) {
        this.fishSupplierId = fishSupplierId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public FishSupplier getFishSupplier() {
        return fishSupplier;
    }

    public void setFishSupplier(FishSupplier fishSupplier) {
        this.fishSupplier = fishSupplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getUnitCostConverted() {
        return unitCostConverted;
    }

    public void setUnitCostConverted(BigDecimal unitCostConverted) {
        this.unitCostConverted = unitCostConverted;
    }

}
