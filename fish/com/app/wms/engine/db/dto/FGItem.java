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
public class FGItem extends AbstractDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer itemId;
    private Integer packId;
    private String itemCode;
    private String itemName;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
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
