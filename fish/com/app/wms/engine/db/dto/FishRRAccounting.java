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
public class FishRRAccounting implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer rrId;
    private String updatedBy;
    private Date updatedDate;

    private FishRr fishRr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRrId() {
        return rrId;
    }

    public void setRrId(Integer rrId) {
        this.rrId = rrId;
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

    public FishRr getFishRr() {
        return fishRr;
    }

    public void setFishRr(FishRr fishRr) {
        this.fishRr = fishRr;
    }

}
