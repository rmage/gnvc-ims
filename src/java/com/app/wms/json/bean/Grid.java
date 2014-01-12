/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.json.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @gnv solution
 */
public class Grid {

    private String page;
    private String total;
    private String records;
    private List rows = new ArrayList();

    public Grid() {
    }

    public Grid(String page, String total, String records) {
        this.page = page;
        this.total = total;
        this.records = records;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
    
    
}
