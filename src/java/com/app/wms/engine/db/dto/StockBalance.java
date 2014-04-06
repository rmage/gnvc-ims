package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockBalance implements Serializable {

    private int id;
    
    private String productCode;
    
    private Date date;
    
    private BigDecimal begin;
    
    private BigDecimal qty_in1;
    
    private BigDecimal qty_in2;
    
    private BigDecimal qty_in3;
    
    private BigDecimal qty_out1;
    
    private BigDecimal qty_out2;
    
    private BigDecimal qty_out3;
    
    private BigDecimal end;
    
    @Override
    public String toString() {
        //FIXME :: StockBalance @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.StockBalance: " );
        
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getBegin() {
        return begin;
    }

    public void setBegin(BigDecimal begin) {
        this.begin = begin;
    }

    public BigDecimal getQty_in1() {
        return qty_in1;
    }

    public void setQty_in1(BigDecimal qty_in1) {
        this.qty_in1 = qty_in1;
    }

    public BigDecimal getQty_in2() {
        return qty_in2;
    }

    public void setQty_in2(BigDecimal qty_in2) {
        this.qty_in2 = qty_in2;
    }

    public BigDecimal getQty_in3() {
        return qty_in3;
    }

    public void setQty_in3(BigDecimal qty_in3) {
        this.qty_in3 = qty_in3;
    }

    public BigDecimal getQty_out1() {
        return qty_out1;
    }

    public void setQty_out1(BigDecimal qty_out1) {
        this.qty_out1 = qty_out1;
    }

    public BigDecimal getQty_out2() {
        return qty_out2;
    }

    public void setQty_out2(BigDecimal qty_out2) {
        this.qty_out2 = qty_out2;
    }

    public BigDecimal getQty_out3() {
        return qty_out3;
    }

    public void setQty_out3(BigDecimal qty_out3) {
        this.qty_out3 = qty_out3;
    }

    public BigDecimal getEnd() {
        return end;
    }

    public void setEnd(BigDecimal end) {
        this.end = end;
    }

}
