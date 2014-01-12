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
public class BundleSearch {

    private String bundleCode = "";
    private String name = "";
    private String tableAlias = "";

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getBundleCode() {
        return bundleCode;
    }

    public void setBundleCode(String bundleCode) {
        this.bundleCode = bundleCode;
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

        if (!this.bundleCode.equals("")) {
            String itemLike = "%" + bundleCode + "%";

            search += " AND " + tableAlias + ".BUNDLE_CODE LIKE :bundleCode ";
            param.put("bundleCode", itemLike);
        }

        if (!this.name.equals("")) {
            String nameLike = "%" + name + "%";

            search += " AND " + tableAlias + ".NAME LIKE :name ";
            param.put("name", nameLike);
        }
        m.put("search", search);
        m.put("parameter", param);
        return m;
    }
}

