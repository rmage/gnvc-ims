/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.bean.json;

/**
 *
 * @gnv solution
 */
public class StockTakeVO {
    private String productCode;
    private String productName;
    private String location;
    private String locationName;

    public StockTakeVO(String productCode, String productName, String location, String locationName) {
        this.productCode = productCode;
        this.productName = productName;
        this.location = location;
        this.locationName = locationName;
    }

    public StockTakeVO() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    
    
}
