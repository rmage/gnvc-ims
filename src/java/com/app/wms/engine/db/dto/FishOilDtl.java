package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FishOilDtl implements Serializable{
    private int id;
    private int foId;
    private Date foDate;
    private int foBiDrums;
    private int foBiKilos;
    private int foRS1Rm;
    private int foRS1Drums;
    private int foRS1Kilos;
    private int foRS2Rm;
    private int foRS2Drums;
    private int foRS2Kilos;
    private int foRS3Rm;
    private int foRS3Drums;
    private int foRS3Kilos;
    private int foTtdDrums;
    private int foTtdKilos;
    private int foIDrums;
    private int foIKilos;
    private BigDecimal foIPrice;
    private int foEiDrums;
    private int foEiKilos;
    private String foRMhrs;
    private String foROthr;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoId() {
        return foId;
    }

    public void setFoId(int foId) {
        this.foId = foId;
    }

    public Date getFoDate() {
        return foDate;
    }

    public void setFoDate(Date foDate) {
        this.foDate = foDate;
    }

    public int getFoBiDrums() {
        return foBiDrums;
    }

    public void setFoBiDrums(int foBiDrums) {
        this.foBiDrums = foBiDrums;
    }

    public int getFoBiKilos() {
        return foBiKilos;
    }

    public void setFoBiKilos(int foBiKilos) {
        this.foBiKilos = foBiKilos;
    }

    public int getFoRS1Rm() {
        return foRS1Rm;
    }

    public void setFoRS1Rm(int foRS1Rm) {
        this.foRS1Rm = foRS1Rm;
    }

    public int getFoRS1Drums() {
        return foRS1Drums;
    }

    public void setFoRS1Drums(int foRS1Drums) {
        this.foRS1Drums = foRS1Drums;
    }

    public int getFoRS1Kilos() {
        return foRS1Kilos;
    }

    public void setFoRS1Kilos(int foRS1Kilos) {
        this.foRS1Kilos = foRS1Kilos;
    }

    public int getFoRS2Rm() {
        return foRS2Rm;
    }

    public void setFoRS2Rm(int foRS2Rm) {
        this.foRS2Rm = foRS2Rm;
    }

    public int getFoRS2Drums() {
        return foRS2Drums;
    }

    public void setFoRS2Drums(int foRS2Drums) {
        this.foRS2Drums = foRS2Drums;
    }

    public int getFoRS2Kilos() {
        return foRS2Kilos;
    }

    public void setFoRS2Kilos(int foRS2Kilos) {
        this.foRS2Kilos = foRS2Kilos;
    }

    public int getFoRS3Rm() {
        return foRS3Rm;
    }

    public void setFoRS3Rm(int foRS3Rm) {
        this.foRS3Rm = foRS3Rm;
    }

    public int getFoRS3Drums() {
        return foRS3Drums;
    }

    public void setFoRS3Drums(int foRS3Drums) {
        this.foRS3Drums = foRS3Drums;
    }

    public int getFoRS3Kilos() {
        return foRS3Kilos;
    }

    public void setFoRS3Kilos(int foRS3Kilos) {
        this.foRS3Kilos = foRS3Kilos;
    }

    public int getFoTtdDrums() {
        return foTtdDrums;
    }

    public void setFoTtdDrums(int foTtdDrums) {
        this.foTtdDrums = foTtdDrums;
    }

    public int getFoTtdKilos() {
        return foTtdKilos;
    }

    public void setFoTtdKilos(int foTtdKilos) {
        this.foTtdKilos = foTtdKilos;
    }

    public int getFoIDrums() {
        return foIDrums;
    }

    public void setFoIDrums(int foIDrums) {
        this.foIDrums = foIDrums;
    }

    public int getFoIKilos() {
        return foIKilos;
    }

    public void setFoIKilos(int foIKilos) {
        this.foIKilos = foIKilos;
    }

    public BigDecimal getFoIPrice() {
        return foIPrice;
    }

    public void setFoIPrice(BigDecimal foIPrice) {
        this.foIPrice = foIPrice;
    }

    public int getFoEiDrums() {
        return foEiDrums;
    }

    public void setFoEiDrums(int foEiDrums) {
        this.foEiDrums = foEiDrums;
    }

    public int getFoEiKilos() {
        return foEiKilos;
    }

    public void setFoEiKilos(int foEiKilos) {
        this.foEiKilos = foEiKilos;
    }

    public String getFoRMhrs() {
        return foRMhrs;
    }

    public void setFoRMhrs(String foRMhrs) {
        this.foRMhrs = foRMhrs;
    }

    public String getFoROthr() {
        return foROthr;
    }

    public void setFoROthr(String foROthr) {
        this.foROthr = foROthr;
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
