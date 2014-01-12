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
public class BundlePriceMap implements ParameterizedRowMapper<BundlePriceMap> {

    protected String bundleCode;
    protected String bundleName;
    protected String catalogCode;
    protected BigDecimal customerPrice;
    protected BigDecimal quantity;

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

    public String getCatalogCode() {
	return catalogCode;
    }

    public void setCatalogCode(String catalogCode) {
	this.catalogCode = catalogCode;
    }

    public BigDecimal getCustomerPrice() {
	return customerPrice;
    }

    public void setCustomerPrice(BigDecimal customerPrice) {
	this.customerPrice = customerPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BundlePriceMap mapRow(ResultSet rs, int i) throws SQLException {
	BundlePriceMap dto = new BundlePriceMap();
	dto.setBundleCode(rs.getString("BUNDLE_CODE"));//BUNDLE_DTL
	dto.setBundleName(rs.getString("BUNDLE_NAME"));//BUNDLE.NAME
	dto.setCatalogCode(rs.getString("CATALOG_CODE"));
	dto.setCustomerPrice(rs.getBigDecimal("CUSTOMER_PRICE"));
	return dto;
    }
}
