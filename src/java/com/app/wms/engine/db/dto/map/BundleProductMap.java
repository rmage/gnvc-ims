/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto.map;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author root
 */
public class BundleProductMap implements ParameterizedRowMapper<BundleProductMap> {

    protected String bundleCode;
    protected String itemCode;
    protected BigDecimal quantity;
    protected BigDecimal createdBy;
    protected Date createdDate;
    protected BigDecimal updatedBy;
    protected Date updatedDate;
    protected String bundleName;
    protected String productName;

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

    public String getItemCode() {
	return itemCode;
    }

    public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
    }

    public String getProductName() {
	return productName;
    }

    public void setProductName(String productName) {
	this.productName = productName;
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

    public BundleProductMap mapRow(ResultSet rs, int i) throws SQLException {
	BundleProductMap dto = new BundleProductMap();
	dto.setBundleCode(rs.getString("BUNDLE_CODE"));//BUNDLE_DTL
	dto.setItemCode(rs.getString("ITEM_CODE"));
	dto.setQuantity(rs.getBigDecimal("QUANTITY"));
	dto.setCreatedBy(rs.getBigDecimal("CREATED_BY"));
	dto.setCreatedDate(rs.getDate("CREATED_DATE"));
	dto.setUpdatedBy(rs.getBigDecimal("UPDATED_BY"));
	dto.setUpdatedDate(rs.getDate("UPDATED_DATE"));
	dto.setBundleName(rs.getString("BUNDLE_NAME"));//BUNDLE.NAME
	dto.setProductName(rs.getString("PRODUCT_NAME"));//PRODUCT.NAME
	return dto;
    }
}
