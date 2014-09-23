/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Faridzi
 */
public class FGPackStyle extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String packStyle;
    private String packSize;
    private Double packPerCs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPackStyle() {
        return packStyle;
    }

    public void setPackStyle(String packStyle) {
        this.packStyle = packStyle;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public Double getPackPerCs() {
        return packPerCs;
    }

    public void setPackPerCs(Double packPerCs) {
        this.packPerCs = packPerCs;
    }

    @Override
    public void setIsActive(String isActive) {
        super.setIsActive(isActive); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIsActive() {
        return super.getIsActive(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        super.setUpdatedBy(updatedBy); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUpdatedBy() {
        return super.getUpdatedBy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        super.setUpdatedDate(updatedDate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getUpdatedDate() {
        return super.getUpdatedDate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatedBy(String createdBy) {
        super.setCreatedBy(createdBy); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCreatedBy() {
        return super.getCreatedBy(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        super.setCreatedDate(createdDate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getCreatedDate() {
        return super.getCreatedDate(); //To change body of generated methods, choose Tools | Templates.
    }

}
