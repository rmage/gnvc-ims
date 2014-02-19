package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BorDtl implements Serializable {

    private int id;
    
    private String borCode;
    
    private String borPackStyle;
    
    private String borCanSize;
    
    private BigDecimal borQty;
    
    private BigDecimal borCs;
    
    private BigDecimal borCnfPrice;
    
    private String borCommission;
    
    private String borBuyer;
    
    private String borBrand;
    
    private String borShipmentDate;
    
    private String borDestinationPort;
    
    private String borPoNumber;
    
    private BigDecimal borODpw;
    
    private String borOFt;
    
    private String borOPt;
    
    private String borOGf;
    
    private String borOCcm;
    
    private String borOOm;
    
    private String borOGmo;
    
    private String borOEc;
    
    private String borOPf;
    
    private String borOOwr;
    
    private String borONol;
    
    private String borOInfo;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        //FIXME :: BorDtl @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.BorDtl: " );
        
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBorCode() {
        return borCode;
    }

    public void setBorCode(String borCode) {
        this.borCode = borCode;
    }

    public String getBorPackStyle() {
        return borPackStyle;
    }

    public void setBorPackStyle(String borPackStyle) {
        this.borPackStyle = borPackStyle;
    }

    public String getBorCanSize() {
        return borCanSize;
    }

    public void setBorCanSize(String borCanSize) {
        this.borCanSize = borCanSize;
    }

    public BigDecimal getBorQty() {
        return borQty;
    }

    public void setBorQty(BigDecimal borQty) {
        this.borQty = borQty;
    }

    public BigDecimal getBorCs() {
        return borCs;
    }

    public void setBorCs(BigDecimal borCs) {
        this.borCs = borCs;
    }

    public BigDecimal getBorCnfPrice() {
        return borCnfPrice;
    }

    public void setBorCnfPrice(BigDecimal borCnfPrice) {
        this.borCnfPrice = borCnfPrice;
    }

    public String getBorCommission() {
        return borCommission;
    }

    public void setBorCommission(String borCommission) {
        this.borCommission = borCommission;
    }

    public String getBorBuyer() {
        return borBuyer;
    }

    public void setBorBuyer(String borBuyer) {
        this.borBuyer = borBuyer;
    }

    public String getBorBrand() {
        return borBrand;
    }

    public void setBorBrand(String borBrand) {
        this.borBrand = borBrand;
    }

    public String getBorShipmentDate() {
        return borShipmentDate;
    }

    public void setBorShipmentDate(String borShipmentDate) {
        this.borShipmentDate = borShipmentDate;
    }

    public String getBorDestinationPort() {
        return borDestinationPort;
    }

    public void setBorDestinationPort(String borDestinationPort) {
        this.borDestinationPort = borDestinationPort;
    }

    public String getBorPoNumber() {
        return borPoNumber;
    }

    public void setBorPoNumber(String borPoNumber) {
        this.borPoNumber = borPoNumber;
    }

    public BigDecimal getBorODpw() {
        return borODpw;
    }

    public void setBorODpw(BigDecimal borODpw) {
        this.borODpw = borODpw;
    }

    public String getBorOFt() {
        return borOFt;
    }

    public void setBorOFt(String borOFt) {
        this.borOFt = borOFt;
    }

    public String getBorOPt() {
        return borOPt;
    }

    public void setBorOPt(String borOPt) {
        this.borOPt = borOPt;
    }

    public String getBorOGf() {
        return borOGf;
    }

    public void setBorOGf(String borOGf) {
        this.borOGf = borOGf;
    }

    public String getBorOCcm() {
        return borOCcm;
    }

    public void setBorOCcm(String borOCcm) {
        this.borOCcm = borOCcm;
    }

    public String getBorOOm() {
        return borOOm;
    }

    public void setBorOOm(String borOOm) {
        this.borOOm = borOOm;
    }

    public String getBorOGmo() {
        return borOGmo;
    }

    public void setBorOGmo(String borOGmo) {
        this.borOGmo = borOGmo;
    }

    public String getBorOEc() {
        return borOEc;
    }

    public void setBorOEc(String borOEc) {
        this.borOEc = borOEc;
    }

    public String getBorOPf() {
        return borOPf;
    }

    public void setBorOPf(String borOPf) {
        this.borOPf = borOPf;
    }

    public String getBorOOwr() {
        return borOOwr;
    }

    public void setBorOOwr(String borOOwr) {
        this.borOOwr = borOOwr;
    }

    public String getBorONol() {
        return borONol;
    }

    public void setBorONol(String borONol) {
        this.borONol = borONol;
    }

    public String getBorOInfo() {
        return borOInfo;
    }

    public void setBorOInfo(String borOInfo) {
        this.borOInfo = borOInfo;
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
