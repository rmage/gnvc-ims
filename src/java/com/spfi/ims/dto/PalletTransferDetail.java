package com.spfi.ims.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PalletTransferDetail implements Serializable {
    
    private int id;
    
    private String code;
    
    private String shift;
    
    private Date pDate;
    
    private String pProdBatch;
    
    private String pBasket;
    
    private BigDecimal pQty;
    
    private int coeFlk;
    
    private int coeNw;
    
    private int coeDw;
    
    private int coePw;
    
    private String qadReleaseTo;
    
    private String qadRemarks;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.spfi.ims.dto.PalletTransferDetail: " );
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getpDate() {
        return pDate;
    }

    public void setpDate(Date pDate) {
        this.pDate = pDate;
    }

    public String getpProdBatch() {
        return pProdBatch;
    }

    public void setpProdBatch(String pProdBatch) {
        this.pProdBatch = pProdBatch;
    }

    public String getpBasket() {
        return pBasket;
    }

    public void setpBasket(String pBasket) {
        this.pBasket = pBasket;
    }

    public BigDecimal getpQty() {
        return pQty;
    }

    public void setpQty(BigDecimal pQty) {
        this.pQty = pQty;
    }

    public int getCoeFlk() {
        return coeFlk;
    }

    public void setCoeFlk(int coeFlk) {
        this.coeFlk = coeFlk;
    }

    public int getCoeNw() {
        return coeNw;
    }

    public void setCoeNw(int coeNw) {
        this.coeNw = coeNw;
    }

    public int getCoeDw() {
        return coeDw;
    }

    public void setCoeDw(int coeDw) {
        this.coeDw = coeDw;
    }

    public int getCoePw() {
        return coePw;
    }

    public void setCoePw(int coePw) {
        this.coePw = coePw;
    }

    public String getQadReleaseTo() {
        return qadReleaseTo;
    }

    public void setQadReleaseTo(String qadReleaseTo) {
        this.qadReleaseTo = qadReleaseTo;
    }

    public String getQadRemarks() {
        return qadRemarks;
    }

    public void setQadRemarks(String qadRemarks) {
        this.qadRemarks = qadRemarks;
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
