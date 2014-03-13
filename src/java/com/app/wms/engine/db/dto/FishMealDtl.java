package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FishMealDtl implements Serializable {
    
    private int id;
    
    private int fmId;
    
    private Date fmDate;
    
    private int fmBiBags;
    
    private int fmBiKilos;
    
    private int fmRS1Rm;
    
    private int fmRS1Bags;
    
    private int fmRS1Kilos;
    
    private int fmRS2Rm;
    
    private int fmRS2Bags;
    
    private int fmRS2Kilos;
    
    private int fmRS3Rm;
    
    private int fmRS3Bags;
    
    private int fmRS3Kilos;
    
    private int fmTtdBags;
    
    private int fmTtdKilos;
    
    private int fmIBags;
    
    private int fmIKilos;
    
    private BigDecimal fmIPrice;
    
    private int fmEiBags;
    
    private int fmEiKilos;
    
    private String fmRMhrs;
    
    private String fmROthr;
    
    private String createdBy;
    
    private Date createdDate;
    
    private String updatedBy;
    
    private Date updatedDate;
    
    @Override
    public String toString() {
        //FIXME :: FishMealDtl @toString completion
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.FishMealDtl: " );
        
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFmId() {
        return fmId;
    }

    public void setFmId(int fmId) {
        this.fmId = fmId;
    }

    public Date getFmDate() {
        return fmDate;
    }

    public void setFmDate(Date fmDate) {
        this.fmDate = fmDate;
    }

    public int getFmBiBags() {
        return fmBiBags;
    }

    public void setFmBiBags(int fmBiBags) {
        this.fmBiBags = fmBiBags;
    }

    public int getFmBiKilos() {
        return fmBiKilos;
    }

    public void setFmBiKilos(int fmBiKilos) {
        this.fmBiKilos = fmBiKilos;
    }

    public int getFmRS1Rm() {
        return fmRS1Rm;
    }

    public void setFmRS1Rm(int fmRS1Rm) {
        this.fmRS1Rm = fmRS1Rm;
    }

    public int getFmRS1Bags() {
        return fmRS1Bags;
    }

    public void setFmRS1Bags(int fmRS1Bags) {
        this.fmRS1Bags = fmRS1Bags;
    }

    public int getFmRS1Kilos() {
        return fmRS1Kilos;
    }

    public void setFmRS1Kilos(int fmRS1Kilos) {
        this.fmRS1Kilos = fmRS1Kilos;
    }

    public int getFmRS2Rm() {
        return fmRS2Rm;
    }

    public void setFmRS2Rm(int fmRS2Rm) {
        this.fmRS2Rm = fmRS2Rm;
    }

    public int getFmRS2Bags() {
        return fmRS2Bags;
    }

    public void setFmRS2Bags(int fmRS2Bags) {
        this.fmRS2Bags = fmRS2Bags;
    }

    public int getFmRS2Kilos() {
        return fmRS2Kilos;
    }

    public void setFmRS2Kilos(int fmRS2Kilos) {
        this.fmRS2Kilos = fmRS2Kilos;
    }

    public int getFmRS3Rm() {
        return fmRS3Rm;
    }

    public void setFmRS3Rm(int fmRS3Rm) {
        this.fmRS3Rm = fmRS3Rm;
    }

    public int getFmRS3Bags() {
        return fmRS3Bags;
    }

    public void setFmRS3Bags(int fmRS3Bags) {
        this.fmRS3Bags = fmRS3Bags;
    }

    public int getFmRS3Kilos() {
        return fmRS3Kilos;
    }

    public void setFmRS3Kilos(int fmRS3Kilos) {
        this.fmRS3Kilos = fmRS3Kilos;
    }

    public int getFmTtdBags() {
        return fmTtdBags;
    }

    public void setFmTtdBags(int fmTtdBags) {
        this.fmTtdBags = fmTtdBags;
    }

    public int getFmTtdKilos() {
        return fmTtdKilos;
    }

    public void setFmTtdKilos(int fmTtdKilos) {
        this.fmTtdKilos = fmTtdKilos;
    }

    public int getFmIBags() {
        return fmIBags;
    }

    public void setFmIBags(int fmIBags) {
        this.fmIBags = fmIBags;
    }

    public int getFmIKilos() {
        return fmIKilos;
    }

    public void setFmIKilos(int fmIKilos) {
        this.fmIKilos = fmIKilos;
    }

    public BigDecimal getFmIPrice() {
        return fmIPrice;
    }

    public void setFmIPrice(BigDecimal fmIPrice) {
        this.fmIPrice = fmIPrice;
    }

    public int getFmEiBags() {
        return fmEiBags;
    }

    public void setFmEiBags(int fmEiBags) {
        this.fmEiBags = fmEiBags;
    }

    public int getFmEiKilos() {
        return fmEiKilos;
    }

    public void setFmEiKilos(int fmEiKilos) {
        this.fmEiKilos = fmEiKilos;
    }

    public String getFmRMhrs() {
        return fmRMhrs;
    }

    public void setFmRMhrs(String fmRMhrs) {
        this.fmRMhrs = fmRMhrs;
    }

    public String getFmROthr() {
        return fmROthr;
    }

    public void setFmROthr(String fmROthr) {
        this.fmROthr = fmROthr;
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
