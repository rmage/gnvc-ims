package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class NonFishDocumentSummary implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private Integer productId;
    private Product product;
    private String documentCode;
    private String documentNumber;
    private Double quantity;
    private BigDecimal amountIDR;

    public NonFishDocumentSummary() {
        this.id = 0;
        this.productId = 0;
        this.product = new Product();
        this.documentCode = "";
        this.documentNumber = "";
        this.quantity = 0d;
        this.amountIDR = BigDecimal.ZERO;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmountIDR() {
        return amountIDR;
    }

    public void setAmountIDR(BigDecimal amountIDR) {
        this.amountIDR = amountIDR;
    }

}
