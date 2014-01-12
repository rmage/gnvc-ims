package com.app.wms.engine.db.dto.map;

import java.math.BigDecimal;
import java.util.Date;


public class BundleDtlItem
{
    protected String bundleCode;
    protected String aliasCode;
    protected BigDecimal quantity;
    protected BigDecimal createdBy;
    protected Date createdDate;
    protected BigDecimal updatedBy;
    protected Date updatedDate;
    protected String bundleName;
    protected String itemCode;
    protected String priceCatalogCode;
    protected String productCatalog;
    protected BigDecimal customerPrice;
    protected String inputNumber; //hold serial/no ident
    protected BigDecimal rangeId;
    protected boolean isSerial;
    protected boolean isCs;
    protected boolean isNs;
    protected boolean isE;
    protected String bundleCatalog;
    protected String nomorHp;

    public String getAliasCode() {
        return aliasCode;
    }

    public void setAliasCode(String aliasCode) {
        this.aliasCode = aliasCode;
    }

    public String getBundleCode() {
        return bundleCode;
    }

    public void setBundleCode(String bundleCode) {
        this.bundleCode = bundleCode;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getPriceCatalogCode() {
        return priceCatalogCode;
    }

    public void setPriceCatalogCode(String priceCatalogCode) {
        this.priceCatalogCode = priceCatalogCode;
    }

    public String getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(String productCatalog) {
        this.productCatalog = productCatalog;
    }

    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(BigDecimal customerPrice) {
        this.customerPrice = customerPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(BigDecimal updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(String inputNumber) {
        this.inputNumber = inputNumber;
    }

    public BigDecimal getRangeId() {
        return rangeId;
    }

    public void setRangeId(BigDecimal rangeId) {
        this.rangeId = rangeId;
    }

    public boolean isIsCs() {
        return isCs;
    }

    public void setIsCs(boolean isCs) {
        this.isCs = isCs;
    }

    public boolean isIsNs() {
        return isNs;
    }

    public void setIsNs(boolean isNs) {
        this.isNs = isNs;
    }

    public boolean isIsSerial() {
        return isSerial;
    }

    public void setIsSerial(boolean isSerial) {
        this.isSerial = isSerial;
    }

    public boolean isIsE() {
        return isE;
    }

    public void setIsE(boolean isE) {
        this.isE = isE;
    }

    public String getBundleCatalog() {
        return bundleCatalog;
    }

    public void setBundleCatalog(String bundleCatalog) {
        this.bundleCatalog = bundleCatalog;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    

}
