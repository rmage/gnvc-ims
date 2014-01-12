/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.web.engine.search;

import java.util.HashMap;

/**
 *
 * @author genevacons
 */
public class CitySearch {

    private String cityCode = "";
    private String name = "";
    private String tableAlias = "";

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap getCriteria() {

        HashMap m = new HashMap();

        String search = " (1=1) ";
        HashMap param = new HashMap();

        if (!this.cityCode.equals("")) {
            // dibikin jgn exact mathing saja lah
            String itemLike = "%" + cityCode + "%";

            search += " AND " + tableAlias + ".CITY_CODE LIKE :cityCode ";
            param.put("cityCode", itemLike);
        }

        if (!this.name.equals("")) {
            // bukan exact mathing
            String nameLike = "%" + name + "%";

            search += " AND " + tableAlias + ".NAME LIKE :name ";
            param.put("name", nameLike);
        }
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }
}

