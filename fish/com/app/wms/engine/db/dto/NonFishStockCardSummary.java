package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NonFishStockCardSummary implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String productCode;
    private Product product;
    private Date asOFDate;
    private Double quantity;
    private BigDecimal unitCost;
    private BigDecimal amountToDate;
    private BigDecimal beginningAmount;
    private BigDecimal transactionAmount;
    private String productCategory;

    public NonFishStockCardSummary() {
        this.id = 0;
        this.productCode = "";
        this.quantity = 0d;
        this.unitCost = BigDecimal.ZERO;
        this.amountToDate = BigDecimal.ZERO;
        this.beginningAmount = BigDecimal.ZERO;
        this.transactionAmount = BigDecimal.ZERO;
        this.product = new Product();
        this.asOFDate = new Date();
        this.productCategory = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getAsOFDate() {
        return asOFDate;
    }

    public void setAsOFDate(Date asOFDate) {
        this.asOFDate = asOFDate;
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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

}
