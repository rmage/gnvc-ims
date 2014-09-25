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
public class FGUnitCost extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer fgItemId;
    private String currencyCode;
    private BigDecimal amountTotal;
    private BigDecimal amountFixCost;
    private BigDecimal amountVarCost;
    private Date unitCostDate;

    private FGItem fgItem;
    
    public FGUnitCost() {
        this.id = 0;
        this.fgItemId = 0;
        this.currencyCode = "USD";
        this.amountFixCost = BigDecimal.ZERO;
        this.amountVarCost = BigDecimal.ZERO;
        this.amountTotal = BigDecimal.ZERO;
        this.unitCostDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFgItemId() {
        return fgItemId;
    }

    public void setFgItemId(Integer fgItemId) {
        this.fgItemId = fgItemId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getAmountFixCost() {
        return amountFixCost;
    }

    public void setAmountFixCost(BigDecimal amountFixCost) {
        this.amountFixCost = amountFixCost;
    }

    public BigDecimal getAmountVarCost() {
        return amountVarCost;
    }

    public void setAmountVarCost(BigDecimal amountVarCost) {
        this.amountVarCost = amountVarCost;
    }

    public Date getUnitCostDate() {
        return unitCostDate;
    }

    public void setUnitCostDate(Date unitCostDate) {
        this.unitCostDate = unitCostDate;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        super.setUpdatedBy(updatedBy); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdatedBy() {
        return super.getUpdatedBy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        super.setUpdatedDate(updatedDate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getUpdatedDate() {
        return super.getUpdatedDate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatedBy(String createdBy) {
        super.setCreatedBy(createdBy); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCreatedBy() {
        return super.getCreatedBy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        super.setCreatedDate(createdDate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getCreatedDate() {
        return super.getCreatedDate(); //To change body of generated methods, choose Tools | Templates.
    }

    public FGItem getFgItem() {
        return fgItem;
    }

    public void setFgItem(FGItem fgItem) {
        this.fgItem = fgItem;
    }

}
