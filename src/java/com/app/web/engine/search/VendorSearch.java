/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.web.engine.search;

import java.util.HashMap;

/**
 *
 * @author Hadi
 */
public class VendorSearch {

    private String vendorCode = "";
    private String name = "";
    private String cityCode = "";
    private String tableAlias = "";

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public HashMap getCriteria() {

        HashMap m = new HashMap();

        String search = " (1=1) ";
        HashMap param = new HashMap();

        if (!this.vendorCode.equals("")) {
            // dibikin jgn exact mathing saja lah
            String itemLike = "%" + vendorCode + "%";

            search += " AND " + tableAlias + ".VENDOR_CODE LIKE :vendorCode ";
            param.put("vendorCode", itemLike);
        }

        if (!this.name.equals("")) {
            // bukan exact mathing
            String nameLike = "%" + name + "%";

            search += " AND " + tableAlias + ".NAME LIKE :name ";
            param.put("name", nameLike);
        }

        if (!this.cityCode.equals("")) {
            search += " AND " + tableAlias + ".CITY_CODE = :cityCode ";
            param.put("cityCode", cityCode);
        }

        m.put("search", search);
        m.put("parameter", param);
        return m;
    }
}

