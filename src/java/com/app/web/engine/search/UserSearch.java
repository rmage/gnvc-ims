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
public class UserSearch {

    private String code = "";
    private String name = "";
    private String tableAlias = "";

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

        if (!this.code.equals("")) {
            String itemLike = "%" + code + "%";

            search += " AND " + tableAlias + ".CODE LIKE :code ";
            param.put("code", itemLike);
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

