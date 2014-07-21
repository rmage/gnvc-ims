package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Prs implements Serializable {

    /**
     * This attribute maps to the column id in the prs table.
     */
    protected int id;

    /**
     * This attribute maps to the column prsnumber in the prs table.
     */
    protected String prsnumber;

    /**
     * This attribute maps to the column prsdate in the prs table.
     */
    protected Date prsdate;

    /**
     * This attribute maps to the column requestdate in the prs table.
     */
    protected Date requestdate;

    /**
     * This attribute maps to the column deliverydate in the prs table.
     */
    protected Date deliverydate;

    /**
     * This attribute maps to the column poreferensi in the prs table.
     */
    protected String poreferensi;

    /**
     * This attribute maps to the column remarks in the prs table.
     */
    protected String remarks;

    /**
     * This attribute maps to the column createdby in the prs table.
     */
    protected String createdby;

    /**
     * This attribute maps to the column department_name in the prs table.
     */
    protected String departmentName;

    /**
     * This attribute maps to the column supplier_name in the prs table.
     *
     */
    protected String isApproved;

    protected String approvedBy;

    protected Date approvedDate;

    protected CanvassingDetail canvassingDetail;

    /**
     * Method 'Prs'
     *
     */
    public Prs() {
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public CanvassingDetail getCanvassingDetail() {
        return canvassingDetail;
    }

    public void setCanvassingDetail(CanvassingDetail canvassingDetail) {
        this.canvassingDetail = canvassingDetail;
    }

    /**
     * Method 'getId'
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Method 'setId'
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method 'getPrsnumber'
     *
     * @return String
     */
    public String getPrsnumber() {
        return prsnumber;
    }

    /**
     * Method 'setPrsnumber'
     *
     * @param prsnumber
     */
    public void setPrsnumber(String prsnumber) {
        this.prsnumber = prsnumber;
    }

    /**
     * Method 'getPrsdate'
     *
     * @return Date
     */
    public Date getPrsdate() {
        return prsdate;
    }

    /**
     * Method 'setPrsdate'
     *
     * @param prsdate
     */
    public void setPrsdate(Date prsdate) {
        this.prsdate = prsdate;
    }

    /**
     * Method 'getRequestdate'
     *
     * @return Date
     */
    public Date getRequestdate() {
        return requestdate;
    }

    /**
     * Method 'setRequestdate'
     *
     * @param requestdate
     */
    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    /**
     * Method 'getDeliverydate'
     *
     * @return Date
     */
    public Date getDeliverydate() {
        return deliverydate;
    }

    /**
     * Method 'setDeliverydate'
     *
     * @param deliverydate
     */
    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    /**
     * Method 'getPoreferensi'
     *
     * @return String
     */
    public String getPoreferensi() {
        return poreferensi;
    }

    /**
     * Method 'setPoreferensi'
     *
     * @param poreferensi
     */
    public void setPoreferensi(String poreferensi) {
        this.poreferensi = poreferensi;
    }

    /**
     * Method 'getRemarks'
     *
     * @return String
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Method 'setRemarks'
     *
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Method 'getCreatedby'
     *
     * @return String
     */
    public String getCreatedby() {
        return createdby;
    }

    /**
     * Method 'setCreatedby'
     *
     * @param createdby
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    /**
     * Method 'getDepartmentName'
     *
     * @return String
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Method 'setDepartmentName'
     *
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

        if (!(_other instanceof Prs)) {
            return false;
        }

        final Prs _cast = (Prs) _other;
        if (id != _cast.id) {
            return false;
        }

        if (prsnumber == null ? _cast.prsnumber != prsnumber : !prsnumber.equals(_cast.prsnumber)) {
            return false;
        }

        if (prsdate == null ? _cast.prsdate != prsdate : !prsdate.equals(_cast.prsdate)) {
            return false;
        }

        if (requestdate == null ? _cast.requestdate != requestdate : !requestdate.equals(_cast.requestdate)) {
            return false;
        }

        if (deliverydate == null ? _cast.deliverydate != deliverydate : !deliverydate.equals(_cast.deliverydate)) {
            return false;
        }

        if (poreferensi == null ? _cast.poreferensi != poreferensi : !poreferensi.equals(_cast.poreferensi)) {
            return false;
        }

        if (remarks == null ? _cast.remarks != remarks : !remarks.equals(_cast.remarks)) {
            return false;
        }

        if (createdby == null ? _cast.createdby != createdby : !createdby.equals(_cast.createdby)) {
            return false;
        }

        if (departmentName == null ? _cast.departmentName != departmentName : !departmentName.equals(_cast.departmentName)) {
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
        _hashCode = 29 * _hashCode + id;
        if (prsnumber != null) {
            _hashCode = 29 * _hashCode + prsnumber.hashCode();
        }

        if (prsdate != null) {
            _hashCode = 29 * _hashCode + prsdate.hashCode();
        }

        if (requestdate != null) {
            _hashCode = 29 * _hashCode + requestdate.hashCode();
        }

        if (deliverydate != null) {
            _hashCode = 29 * _hashCode + deliverydate.hashCode();
        }

        if (poreferensi != null) {
            _hashCode = 29 * _hashCode + poreferensi.hashCode();
        }

        if (remarks != null) {
            _hashCode = 29 * _hashCode + remarks.hashCode();
        }

        if (createdby != null) {
            _hashCode = 29 * _hashCode + createdby.hashCode();
        }

        if (departmentName != null) {
            _hashCode = 29 * _hashCode + departmentName.hashCode();
        }

        return _hashCode;
    }

    /**
     * Method 'createPk'
     *
     * @return PrsPk
     */
    public PrsPk createPk() {
        return new PrsPk(id);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("com.app.wms.engine.db.dto.Prs: ");
        ret.append("id=" + id);
        ret.append(", prsnumber=" + prsnumber);
        ret.append(", prsdate=" + prsdate);
        ret.append(", requestdate=" + requestdate);
        ret.append(", deliverydate=" + deliverydate);
        ret.append(", poreferensi=" + poreferensi);
        ret.append(", remarks=" + remarks);
        ret.append(", createdby=" + createdby);
        ret.append(", departmentName=" + departmentName);
        ret.append(", isApproved=" + isApproved);
        ret.append(", approvedBy=" + approvedBy);
        ret.append(", approvedDate=" + approvedDate);
        return ret.toString();
    }

}
