package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class Vendor implements Serializable {

    /**
     * This attribute maps to the column VENDOR_CODE in the VENDOR table.
     */
    protected String vendorCode;
    /**
     * This attribute maps to the column NAME in the VENDOR table.
     */
    protected String name;
    /**
     * This attribute maps to the column ADDRESS in the VENDOR table.
     */
    protected String address;
    /**
     * This attribute maps to the column PHONE in the VENDOR table.
     */
    protected String phone;
    /**
     * This attribute maps to the column EMAIL in the VENDOR table.
     */
    protected String email;
    /**
     * This attribute maps to the column CITY_CODE in the VENDOR table.
     */
    protected String cityCode;
    /**
     * This attribute maps to the column CREATED_BY in the VENDOR table.
     */
    protected BigDecimal createdBy;
    /**
     * This attribute maps to the column CREATED_DATE in the VENDOR table.
     */
    protected Date createdDate;
    /**
     * This attribute maps to the column UPDATED_BY in the VENDOR table.
     */
    protected BigDecimal updatedBy;
    /**
     * This attribute maps to the column UPDATED_DATE in the VENDOR table.
     */
    protected Date updatedDate;

    /**
     * Method 'Vendor'
     *
     */
    public Vendor() {
    }

    /**
     * Method 'getVendorCode'
     *
     * @return String
     */
    public String getVendorCode() {
        return vendorCode;
    }

    /**
     * Method 'setVendorCode'
     *
     * @param vendorCode
     */
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    /**
     * Method 'getName'
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method 'setName'
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method 'getAddress'
     *
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method 'setAddress'
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method 'getPhone'
     *
     * @return String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method 'setPhone'
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Method 'getEmail'
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method 'setEmail'
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method 'getCityCode'
     * 
     * @return String
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Method 'setCityCode'
     * 
     * @param cityCode
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Method 'getCreatedBy'
     *
     * @return BigDecimal
     */
    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    /**
     * Method 'setCreatedBy'
     *
     * @param createdBy
     */
    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Method 'getCreatedDate'
     *
     * @return Date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Method 'setCreatedDate'
     *
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Method 'getUpdatedBy'
     *
     * @return BigDecimal
     */
    public BigDecimal getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Method 'setUpdatedBy'
     *
     * @param updatedBy
     */
    public void setUpdatedBy(BigDecimal updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Method 'getUpdatedDate'
     *
     * @return Date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Method 'setUpdatedDate'
     *
     * @param updatedDate
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Method 'equals'
     *
     * @param _other
     * @return boolean
     */
    public boolean equals(Object _other) {
        if (_other == null) {
            return false;
        }

        if (_other == this) {
            return true;
        }

        if (!(_other instanceof Vendor)) {
            return false;
        }

        final Vendor _cast = (Vendor) _other;
        if (vendorCode == null ? _cast.vendorCode != vendorCode : !vendorCode.equals(_cast.vendorCode)) {
            return false;
        }

        if (name == null ? _cast.name != name : !name.equals(_cast.name)) {
            return false;
        }

        if (address == null ? _cast.address != address : !address.equals(_cast.address)) {
            return false;
        }

        if (phone == null ? _cast.phone != phone : !phone.equals(_cast.phone)) {
            return false;
        }

        if (email == null ? _cast.email != email : !email.equals(_cast.email)) {
            return false;
        }

        if (cityCode == null ? _cast.cityCode != cityCode : !cityCode.equals(_cast.cityCode)) {
            return false;
        }

        if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals(_cast.createdBy)) {
            return false;
        }

        if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals(_cast.createdDate)) {
            return false;
        }

        if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals(_cast.updatedBy)) {
            return false;
        }

        if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals(_cast.updatedDate)) {
            return false;
        }

        return true;
    }

    /**
     * Method 'hashCode'
     *
     * @return int
     */
    public int hashCode() {
        int _hashCode = 0;
        if (vendorCode != null) {
            _hashCode = 29 * _hashCode + vendorCode.hashCode();
        }

        if (name != null) {
            _hashCode = 29 * _hashCode + name.hashCode();
        }

        if (address != null) {
            _hashCode = 29 * _hashCode + address.hashCode();
        }

        if (phone != null) {
            _hashCode = 29 * _hashCode + phone.hashCode();
        }

        if (email != null) {
            _hashCode = 29 * _hashCode + email.hashCode();
        }

        if (cityCode != null) {
            _hashCode = 29 * _hashCode + cityCode.hashCode();
        }

        if (createdBy != null) {
            _hashCode = 29 * _hashCode + createdBy.hashCode();
        }

        if (createdDate != null) {
            _hashCode = 29 * _hashCode + createdDate.hashCode();
        }

        if (updatedBy != null) {
            _hashCode = 29 * _hashCode + updatedBy.hashCode();
        }

        if (updatedDate != null) {
            _hashCode = 29 * _hashCode + updatedDate.hashCode();
        }

        return _hashCode;
    }

    /**
     * Method 'createPk'
     *
     * @return VendorPk
     */
    public VendorPk createPk() {
        return new VendorPk(vendorCode);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("com.app.wms.engine.db.dto.Vendor: ");
        ret.append("vendorCode=" + vendorCode);
        ret.append(", name=" + name);
        ret.append(", address=" + address);
        ret.append(", phone=" + phone);
        ret.append(", email=" + email);
        ret.append(", cityCode=" + cityCode);
        ret.append(", createdBy=" + createdBy);
        ret.append(", createdDate=" + createdDate);
        ret.append(", updatedBy=" + updatedBy);
        ret.append(", updatedDate=" + updatedDate);
        return ret.toString();
    }
}
