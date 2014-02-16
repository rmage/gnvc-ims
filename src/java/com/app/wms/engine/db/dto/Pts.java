package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Pts implements Serializable {

    private int ptsCode;
    
    private Date ptsDate;
    
    private String ptsCanCode;
    
    private BigDecimal ptsCs;
    
    private String ptsLocation;
    
    private String productCode;
    
    private String borCode;
    
    private BigDecimal coeFlk;
    
    private BigDecimal coeNw;
    
    private BigDecimal coeDw;
    
    private BigDecimal coePw;
    
    private String qadReleaseTo;
    
    private String qadRemarks;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.Pts: " );
        sb.append("ptsCode = ").append( getPtsCode() );
        sb.append(", ptsDate = ").append( getPtsDate() );
        sb.append(", ptsCanCode = ").append( getPtsCanCode() );
        sb.append(", ptsCs = ").append( getPtsCs() );
        sb.append(", ptsLocation = ").append( getPtsLocation() );
        sb.append(", productCode = ").append( getProductCode() );
        sb.append(", borCode = ").append( getBorCode() );
        sb.append(", coeFlk = ").append( getCoeFlk() );
        sb.append(", coeNw = ").append( getCoeNw() );
        sb.append(", coeDw = ").append( getCoeDw() );
        sb.append(", coePw = ").append( getCoePw() );
        sb.append(", qadReleaseTo = ").append( getQadReleaseTo());
        sb.append(", qadRemarks = ").append( getQadRemarks() );
        sb.append(", createdBy = ").append( getCreatedBy() );
        sb.append(", createdDate = ").append( getCreatedDate() );
        sb.append(", updatedBy = ").append( getUpdatedBy() );
        sb.append(", updatedDate = ").append( getUpdatedDate() );
        return sb.toString();
    }

    public int getPtsCode() {
        return ptsCode;
    }

    public void setPtsCode(int ptsCode) {
        this.ptsCode = ptsCode;
    }

    public Date getPtsDate() {
        return ptsDate;
    }

    public void setPtsDate(Date ptsDate) {
        this.ptsDate = ptsDate;
    }

    public String getPtsCanCode() {
        return ptsCanCode;
    }

    public void setPtsCanCode(String ptsCanCode) {
        this.ptsCanCode = ptsCanCode;
    }

    public BigDecimal getPtsCs() {
        return ptsCs;
    }

    public void setPtsCs(BigDecimal ptsCs) {
        this.ptsCs = ptsCs;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getBorCode() {
        return borCode;
    }

    public void setBorCode(String borCode) {
        this.borCode = borCode;
    }

    public BigDecimal getCoeFlk() {
        return coeFlk;
    }

    public void setCoeFlk(BigDecimal coeFlk) {
        this.coeFlk = coeFlk;
    }

    public BigDecimal getCoeNw() {
        return coeNw;
    }

    public void setCoeNw(BigDecimal coeNw) {
        this.coeNw = coeNw;
    }

    public BigDecimal getCoeDw() {
        return coeDw;
    }

    public void setCoeDw(BigDecimal coeDw) {
        this.coeDw = coeDw;
    }

    public BigDecimal getCoePw() {
        return coePw;
    }

    public void setCoePw(BigDecimal coePw) {
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

    public String getPtsLocation() {
        return ptsLocation;
    }

    public void setPtsLocation(String ptsLocation) {
        this.ptsLocation = ptsLocation;
    }
    
}
