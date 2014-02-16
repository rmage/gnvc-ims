package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PtsDtl implements Serializable {
    
    private int id;
    
    private int ptsCode;
    
    private String ptsShift;
    
    private Date ptsDate;
    
    private String ptsProdBatch;
    
    private String ptsBasket;
    
    private BigDecimal ptsQty;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.PtsDetail: " );
        sb.append("id = ").append( getId() );
        sb.append(", ptsCode = ").append( getPtsCode() );
        sb.append(", ptsShift = ").append( getPtsShift() );
        sb.append(", ptsDate = ").append( getPtsDate() );
        sb.append(", ptsProdBatch = ").append( getPtsProdBatch() );
        sb.append(", ptsBasket = ").append( getPtsBasket() );
        sb.append(", createdBy = ").append( getCreatedBy() );
        sb.append(", createdDate = ").append( getCreatedDate() );
        sb.append(", updatedBy = ").append( getUpdatedBy() );
        sb.append(", updatedDate = ").append( getUpdatedDate() ); 
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPtsCode() {
        return ptsCode;
    }

    public void setPtsCode(int ptsCode) {
        this.ptsCode = ptsCode;
    }

    public String getPtsShift() {
        return ptsShift;
    }

    public void setPtsShift(String ptsShift) {
        this.ptsShift = ptsShift;
    }

    public Date getPtsDate() {
        return ptsDate;
    }

    public void setPtsDate(Date ptsDate) {
        this.ptsDate = ptsDate;
    }

    public String getPtsProdBatch() {
        return ptsProdBatch;
    }

    public void setPtsProdBatch(String ptsProdBatch) {
        this.ptsProdBatch = ptsProdBatch;
    }

    public String getPtsBasket() {
        return ptsBasket;
    }

    public void setPtsBasket(String ptsBasket) {
        this.ptsBasket = ptsBasket;
    }

    public BigDecimal getPtsQty() {
        return ptsQty;
    }

    public void setPtsQty(BigDecimal ptsQty) {
        this.ptsQty = ptsQty;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
}
