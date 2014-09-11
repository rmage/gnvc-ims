package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class FishStockCardAccounting implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer fishId;
    private Double begQuantity;
    private BigDecimal begUnitCost;
    private BigDecimal begAmount;
    private Double receiveQuantity;
    private BigDecimal receiveUnitCost;
    private BigDecimal receiveAmount;
    private Double withdrawalQuantity;
    private BigDecimal withdrawalUnitCost;
    private BigDecimal withdrawalAmount;
    private Double endQuantity;
    private BigDecimal endUnitCost;
    private BigDecimal endAmount;
    
    private Fish fish;

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

    public Double getBegQuantity() {
        return begQuantity;
    }

    public void setBegQuantity(Double begQuantity) {
        this.begQuantity = begQuantity;
    }

    public BigDecimal getBegUnitCost() {
        return begUnitCost;
    }

    public void setBegUnitCost(BigDecimal begUnitCost) {
        this.begUnitCost = begUnitCost;
    }

    public BigDecimal getBegAmount() {
        return begAmount;
    }

    public void setBegAmount(BigDecimal begAmount) {
        this.begAmount = begAmount;
    }

    public Double getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(Double receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getReceiveUnitCost() {
        return receiveUnitCost;
    }

    public void setReceiveUnitCost(BigDecimal receiveUnitCost) {
        this.receiveUnitCost = receiveUnitCost;
    }

    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public Double getWithdrawalQuantity() {
        return withdrawalQuantity;
    }

    public void setWithdrawalQuantity(Double withdrawalQuantity) {
        this.withdrawalQuantity = withdrawalQuantity;
    }

    public BigDecimal getWithdrawalUnitCost() {
        return withdrawalUnitCost;
    }

    public void setWithdrawalUnitCost(BigDecimal withdrawalUnitCost) {
        this.withdrawalUnitCost = withdrawalUnitCost;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public Double getEndQuantity() {
        return endQuantity;
    }

    public void setEndQuantity(Double endQuantity) {
        this.endQuantity = endQuantity;
    }

    public BigDecimal getEndUnitCost() {
        return endUnitCost;
    }

    public void setEndUnitCost(BigDecimal endUnitCost) {
        this.endUnitCost = endUnitCost;
    }

    public BigDecimal getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(BigDecimal endAmount) {
        this.endAmount = endAmount;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

}
