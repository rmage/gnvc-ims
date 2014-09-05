package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class NonFishDocumentSummary implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String productId;
    private Product product;
    private BigDecimal amountIDR;

    public NonFishDocumentSummary() {
        this.id = 0;
        this.productId = "";
        this.product = new Product();
        this.amountIDR = BigDecimal.ZERO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getAmountIDR() {
        return amountIDR;
    }

    public void setAmountIDR(BigDecimal amountIDR) {
        this.amountIDR = amountIDR;
    }

}
