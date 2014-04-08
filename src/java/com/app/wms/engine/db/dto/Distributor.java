package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Distributor implements Serializable {

    private int id;
    private String distributorCode;
    private String distributorName;
    private String distributorAddress;
    private String telephone;
    private String fax;
    private String email;
    private String contactPerson;
    private String isActive;
    private String isDelete;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append( "com.app.wms.engine.db.dto.Distributor: " );
        sb.append("id = ").append( getId());
        sb.append("distributorCode = ").append( getDistributorCode());
        sb.append("distributorName = ").append( getDistributorName());
        sb.append("distributorAddress = ").append( getDistributorAddress());
        sb.append("telephone = ").append( getTelephone());
        sb.append("fax = ").append( getFax());
        sb.append("email = ").append( getEmail());
        sb.append("contactPerson = ").append( getContactPerson());
        sb.append("isActive = ").append( getIsActive());
        sb.append("isDelete = ").append( getIsDelete());
        sb.append("createdBy = ").append( getCreatedBy());
        sb.append("createdDate = ").append( getCreatedDate());
        sb.append("updatedBy = ").append( getUpdatedBy());
        sb.append("updatedDate = ").append( getUpdatedDate());
        return sb.toString();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistributorCode() {
        return distributorCode;
    }

    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getDistributorAddress() {
        return distributorAddress;
    }

    public void setDistributorAddress(String distributorAddress) {
        this.distributorAddress = distributorAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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
