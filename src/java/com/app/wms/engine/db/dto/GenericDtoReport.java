/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.engine.db.dto;

import java.util.ArrayList;

/**
 *
 * @author zyrex
 */
public class GenericDtoReport {

    protected String location;
    protected String date;
    protected String startDate;
    protected String endDate;
    protected String dealerCode;
    protected String username;
    protected String itemType;
    protected String month;
    protected String startMonth;
    protected String endMonth;
    protected String year;
    protected String bgType;
    private String catalogCode;
    private String confirmDate;
    private ArrayList<String> parameterValues = new ArrayList<String>();
    private ArrayList<String> parameterKeys = new ArrayList<String>();
    private String outputFormat;

    public GenericDtoReport(){
        
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        //System.out.println("call set location with param: " + location);
        this.getParameterKeys().add("P_LOCATION");
        getParameterValues().add(location);
        this.location = location;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        System.out.println("value of date is:" + date);
        this.getParameterKeys().add("P_DATE");
        getParameterValues().add(date);
        this.date = date;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        System.out.println("value of start date is:" + startDate);
        getParameterValues().add(startDate);
        this.getParameterKeys().add("P_START_DATE");
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        System.out.println("value of end date is:" + endDate);
        getParameterValues().add(endDate);
        this.getParameterKeys().add("P_END_DATE");
        this.endDate = endDate;
    }

    /**
     * @return the dealerCode
     */
    public String getDealerCode() {
        return dealerCode;
    }

    /**
     * @param dealerCode the dealerCode to set
     */
    public void setDealerCode(String dealerCode) {
        getParameterValues().add(dealerCode);
        this.getParameterKeys().add("P_DEALER_CODE");
        this.dealerCode = dealerCode;
    }

    /**
     * @return the userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param userName the userName to set
     */
    public void setUsername(String userName) {
        getParameterValues().add(userName);
        this.getParameterKeys().add("P_USERNAME");
        this.username = userName;
    }

    /**
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        //System.out.println("call setItemType");
        getParameterValues().add(itemType);
        this.getParameterKeys().add("P_ITEM_TYPE");
        this.itemType = itemType;
    }

    /**
     * @return the outputFormat
     */
    public String getOutputFormat() {
        return outputFormat;
    }

    /**
     * @param outputFormat the outputFormat to set
     */
    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
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

        if (!(_other instanceof GenericDtoReport)) {
            return false;
        }

        final GenericDtoReport _cast = (GenericDtoReport) _other;

        if (location == null ? _cast.location != location : !location.equals(_cast.location)) {
            return false;
        }

        if (date == null ? _cast.date != date : !date.equals(_cast.date)) {
            return false;
        }

        if (dealerCode == null ? _cast.dealerCode != dealerCode : !dealerCode.equals(_cast.dealerCode)) {
            return false;
        }

        if (endDate == null ? _cast.endDate != endDate : !endDate.equals(_cast.endDate)) {
            return false;
        }

        if (itemType == null ? _cast.itemType != itemType : !itemType.equals(_cast.itemType)) {
            return false;
        }

        if (startDate == null ? _cast.startDate != startDate : !startDate.equals(_cast.startDate)) {
            return false;
        }

        if (username == null ? _cast.username != username : !username.equals(_cast.username)) {
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
        if (date != null) {
            _hashCode = 29 * _hashCode + date.hashCode();
        }

        if (dealerCode != null) {
            _hashCode = 29 * _hashCode + dealerCode.hashCode();
        }

        if (endDate != null) {
            _hashCode = 29 * _hashCode + endDate.hashCode();
        }

        if (itemType != null) {
            _hashCode = 29 * _hashCode + itemType.hashCode();
        }

        if (location != null) {
            _hashCode = 29 * _hashCode + location.hashCode();
        }

        if (startDate != null) {
            _hashCode = 29 * _hashCode + startDate.hashCode();
        }

        if (username != null) {
            _hashCode = 29 * _hashCode + username.hashCode();
        }
        return _hashCode;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("com.caraka.wms.engine.db.dto.GenericDtoReport: ");
        ret.append("location=" + location);
        ret.append(", date=" + date);
        ret.append(", dealerCode=" + dealerCode);
        ret.append(", endDate=" + endDate);
        ret.append(", itemType=" + itemType);
        ret.append(", startDate=" + startDate);
        ret.append(", userName=" + username);
        return ret.toString();
    }

    /**
     * @return the parameterValues
     */
    public ArrayList<String> getParameterValues() {
        return parameterValues;
    }

    /**
     * @param parameterValues the parameterValues to set
     */
    public void setParameterValues(ArrayList<String> parameterValues) {
        this.parameterValues = parameterValues;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        getParameterValues().add(month);
        this.getParameterKeys().add("P_MONTH");
        this.month = month;
    }

    /**
     * @return the startMonth
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * @param startMonth the startMonth to set
     */
    public void setStartMonth(String startMonth) {
        getParameterValues().add(startMonth);
        this.getParameterKeys().add("P_START_MONTH");
        this.startMonth = startMonth;
    }

    /**
     * @return the endMonth
     */
    public String getEndMonth() {
        return endMonth;
    }

    /**
     * @param endMonth the endMonth to set
     */
    public void setEndMonth(String endMonth) {
        this.getParameterKeys().add("P_END_MONTH");
        getParameterValues().add(endMonth);
        this.endMonth = endMonth;
    }

    /**
     * @return the parameterKeys
     */
    public ArrayList<String> getParameterKeys() {
        return parameterKeys;
    }

    /**
     * @param parameterKeys the parameterKeys to set
     */
    public void setParameterKeys(ArrayList<String> parameterKeys) {
        this.parameterKeys = parameterKeys;
    }

    /**
     * @return the bgType
     */
    public String getBgType() {
        return bgType;
    }

    /**
     * @param bgType the bgType to set
     */
    public void setBgType(String bgType) {
        this.getParameterKeys().add("P_BG_TYPE");
        getParameterValues().add(bgType);
        this.bgType = bgType;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.getParameterKeys().add("P_YEAR");
        getParameterValues().add(year);
        this.year = year;
    }

    /**
     * @return the catalogCode
     */
    public String getCatalogCode() {
        return catalogCode;
    }

    /**
     * @param catalogCode the catalogCode to set
     */
    public void setCatalogCode(String catalogCode) {
        this.getParameterKeys().add("P_CATALOG_CODE");
        getParameterValues().add(catalogCode);
        this.catalogCode = catalogCode;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.getParameterKeys().add("P_CONFIRM_DATE");
        getParameterValues().add(confirmDate);
        this.confirmDate = confirmDate;
    }  

}
