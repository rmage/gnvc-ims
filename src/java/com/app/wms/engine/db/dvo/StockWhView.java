/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dvo;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.dto.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;

/**
 *
 * @author Programmer
 */
public class StockWhView implements Serializable {

    public StockWhView() {
    }
    protected String stockCode;
    protected String itemCode;
    protected String itemName;
    protected String itemCatalogCode;
    protected String whCode;
    protected String whName;
    protected BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getItemCatalogCode() {
        return itemCatalogCode;
    }

    public void setItemCatalogCode(String itemCatalogCode) {
        this.itemCatalogCode = itemCatalogCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }
}
