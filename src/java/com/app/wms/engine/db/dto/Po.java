package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Po implements Serializable
{
	/** 
	 * This attribute maps to the column id in the po table.
	 */
	protected long id;

	/** 
	 * This attribute maps to the column ponumber in the po table.
	 */
	protected String ponumber;

	/** 
	 * This attribute maps to the column podate in the po table.
	 */
	protected Date podate;

	/** 
	 * This attribute maps to the column prsnumber in the po table.
	 */
	protected String prsnumber;

	/** 
	 * This attribute maps to the column prsdate in the po table.
	 */
	protected Date prsdate;

	/** 
	 * This attribute maps to the column deliverydate in the po table.
	 */
	protected Date deliverydate;

	/** 
	 * This attribute maps to the column poreferensi in the po table.
	 */
	protected String poreferensi;

	/** 
	 * This attribute maps to the column createdby in the po table.
	 */
	protected String createdby;

	/** 
	 * This attribute maps to the column corpid in the po table.
	 */
	protected String corpid;

	/** 
	 * This attribute maps to the column wh_code in the po table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column department_name in the po table.
	 */
	protected String departmentName;

	/** 
	 * This attribute maps to the column supplier_name in the po table.
	 */
	protected String supplierName;

	/** 
	 * This attribute maps to the column currency in the po table.
	 */
	protected String currency;

	/** 
	 * This attribute maps to the column prsremarks in the po table.
	 */
	protected String prsremarks;

	/** 
	 * This attribute maps to the column role_code in the po table.
	 */
	protected String roleCode;

	/** 
	 * This attribute maps to the column status in the po table.
	 */
	protected String status;

	protected Date statusdate;
	/**
	 * Method 'Po'
	 * 
	 */
	public Po()
	{
	}

	public Date getStatusdate() {
		return statusdate;
	}

	public void setStatusdate(Date statusdate) {
		this.statusdate = statusdate;
	}

	/**
	 * Method 'getId'
	 * 
	 * @return long
	 */
	public long getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * Method 'getPonumber'
	 * 
	 * @return String
	 */
	public String getPonumber()
	{
		return ponumber;
	}

	/**
	 * Method 'setPonumber'
	 * 
	 * @param ponumber
	 */
	public void setPonumber(String ponumber)
	{
		this.ponumber = ponumber;
	}

	/**
	 * Method 'getPodate'
	 * 
	 * @return Date
	 */
	public Date getPodate()
	{
		return podate;
	}

	/**
	 * Method 'setPodate'
	 * 
	 * @param podate
	 */
	public void setPodate(Date podate)
	{
		this.podate = podate;
	}

	/**
	 * Method 'getPrsnumber'
	 * 
	 * @return String
	 */
	public String getPrsnumber()
	{
		return prsnumber;
	}

	/**
	 * Method 'setPrsnumber'
	 * 
	 * @param prsnumber
	 */
	public void setPrsnumber(String prsnumber)
	{
		this.prsnumber = prsnumber;
	}

	/**
	 * Method 'getPrsdate'
	 * 
	 * @return Date
	 */
	public Date getPrsdate()
	{
		return prsdate;
	}

	/**
	 * Method 'setPrsdate'
	 * 
	 * @param prsdate
	 */
	public void setPrsdate(Date prsdate)
	{
		this.prsdate = prsdate;
	}

	/**
	 * Method 'getDeliverydate'
	 * 
	 * @return Date
	 */
	public Date getDeliverydate()
	{
		return deliverydate;
	}

	/**
	 * Method 'setDeliverydate'
	 * 
	 * @param deliverydate
	 */
	public void setDeliverydate(Date deliverydate)
	{
		this.deliverydate = deliverydate;
	}

	/**
	 * Method 'getPoreferensi'
	 * 
	 * @return String
	 */
	public String getPoreferensi()
	{
		return poreferensi;
	}

	/**
	 * Method 'setPoreferensi'
	 * 
	 * @param poreferensi
	 */
	public void setPoreferensi(String poreferensi)
	{
		this.poreferensi = poreferensi;
	}

	/**
	 * Method 'getCreatedby'
	 * 
	 * @return String
	 */
	public String getCreatedby()
	{
		return createdby;
	}

	/**
	 * Method 'setCreatedby'
	 * 
	 * @param createdby
	 */
	public void setCreatedby(String createdby)
	{
		this.createdby = createdby;
	}

	/**
	 * Method 'getCorpid'
	 * 
	 * @return String
	 */
	public String getCorpid()
	{
		return corpid;
	}

	/**
	 * Method 'setCorpid'
	 * 
	 * @param corpid
	 */
	public void setCorpid(String corpid)
	{
		this.corpid = corpid;
	}

	/**
	 * Method 'getWhCode'
	 * 
	 * @return String
	 */
	public String getWhCode()
	{
		return whCode;
	}

	/**
	 * Method 'setWhCode'
	 * 
	 * @param whCode
	 */
	public void setWhCode(String whCode)
	{
		this.whCode = whCode;
	}

	/**
	 * Method 'getDepartmentName'
	 * 
	 * @return String
	 */
	public String getDepartmentName()
	{
		return departmentName;
	}

	/**
	 * Method 'setDepartmentName'
	 * 
	 * @param departmentName
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 * Method 'getSupplierName'
	 * 
	 * @return String
	 */
	public String getSupplierName()
	{
		return supplierName;
	}

	/**
	 * Method 'setSupplierName'
	 * 
	 * @param supplierName
	 */
	public void setSupplierName(String supplierName)
	{
		this.supplierName = supplierName;
	}

	/**
	 * Method 'getCurrency'
	 * 
	 * @return String
	 */
	public String getCurrency()
	{
		return currency;
	}

	/**
	 * Method 'setCurrency'
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	/**
	 * Method 'getPrsremarks'
	 * 
	 * @return String
	 */
	public String getPrsremarks()
	{
		return prsremarks;
	}

	/**
	 * Method 'setPrsremarks'
	 * 
	 * @param prsremarks
	 */
	public void setPrsremarks(String prsremarks)
	{
		this.prsremarks = prsremarks;
	}

	/**
	 * Method 'getRoleCode'
	 * 
	 * @return String
	 */
	public String getRoleCode()
	{
		return roleCode;
	}

	/**
	 * Method 'setRoleCode'
	 * 
	 * @param roleCode
	 */
	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	/**
	 * Method 'getStatus'
	 * 
	 * @return String
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Method 'setStatus'
	 * 
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof Po)) {
			return false;
		}
		
		final Po _cast = (Po) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (ponumber == null ? _cast.ponumber != ponumber : !ponumber.equals( _cast.ponumber )) {
			return false;
		}
		
		if (podate == null ? _cast.podate != podate : !podate.equals( _cast.podate )) {
			return false;
		}
		
		if (prsnumber == null ? _cast.prsnumber != prsnumber : !prsnumber.equals( _cast.prsnumber )) {
			return false;
		}
		
		if (prsdate == null ? _cast.prsdate != prsdate : !prsdate.equals( _cast.prsdate )) {
			return false;
		}
		
		if (deliverydate == null ? _cast.deliverydate != deliverydate : !deliverydate.equals( _cast.deliverydate )) {
			return false;
		}
		
		if (poreferensi == null ? _cast.poreferensi != poreferensi : !poreferensi.equals( _cast.poreferensi )) {
			return false;
		}
		
		if (createdby == null ? _cast.createdby != createdby : !createdby.equals( _cast.createdby )) {
			return false;
		}
		
		if (corpid == null ? _cast.corpid != corpid : !corpid.equals( _cast.corpid )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
			return false;
		}
		
		if (departmentName == null ? _cast.departmentName != departmentName : !departmentName.equals( _cast.departmentName )) {
			return false;
		}
		
		if (supplierName == null ? _cast.supplierName != supplierName : !supplierName.equals( _cast.supplierName )) {
			return false;
		}
		
		if (currency == null ? _cast.currency != currency : !currency.equals( _cast.currency )) {
			return false;
		}
		
		if (prsremarks == null ? _cast.prsremarks != prsremarks : !prsremarks.equals( _cast.prsremarks )) {
			return false;
		}
		
		if (roleCode == null ? _cast.roleCode != roleCode : !roleCode.equals( _cast.roleCode )) {
			return false;
		}
		
		if (status == null ? _cast.status != status : !status.equals( _cast.status )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		_hashCode = 29 * _hashCode + (int) (id ^ (id >>> 32));
		if (ponumber != null) {
			_hashCode = 29 * _hashCode + ponumber.hashCode();
		}
		
		if (podate != null) {
			_hashCode = 29 * _hashCode + podate.hashCode();
		}
		
		if (prsnumber != null) {
			_hashCode = 29 * _hashCode + prsnumber.hashCode();
		}
		
		if (prsdate != null) {
			_hashCode = 29 * _hashCode + prsdate.hashCode();
		}
		
		if (deliverydate != null) {
			_hashCode = 29 * _hashCode + deliverydate.hashCode();
		}
		
		if (poreferensi != null) {
			_hashCode = 29 * _hashCode + poreferensi.hashCode();
		}
		
		if (createdby != null) {
			_hashCode = 29 * _hashCode + createdby.hashCode();
		}
		
		if (corpid != null) {
			_hashCode = 29 * _hashCode + corpid.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		if (departmentName != null) {
			_hashCode = 29 * _hashCode + departmentName.hashCode();
		}
		
		if (supplierName != null) {
			_hashCode = 29 * _hashCode + supplierName.hashCode();
		}
		
		if (currency != null) {
			_hashCode = 29 * _hashCode + currency.hashCode();
		}
		
		if (prsremarks != null) {
			_hashCode = 29 * _hashCode + prsremarks.hashCode();
		}
		
		if (roleCode != null) {
			_hashCode = 29 * _hashCode + roleCode.hashCode();
		}
		
		if (status != null) {
			_hashCode = 29 * _hashCode + status.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return PoPk
	 */
	public PoPk createPk()
	{
		return new PoPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Po: " );
		ret.append( "id=" + id );
		ret.append( ", ponumber=" + ponumber );
		ret.append( ", podate=" + podate );
		ret.append( ", prsnumber=" + prsnumber );
		ret.append( ", prsdate=" + prsdate );
		ret.append( ", deliverydate=" + deliverydate );
		ret.append( ", poreferensi=" + poreferensi );
		ret.append( ", createdby=" + createdby );
		ret.append( ", corpid=" + corpid );
		ret.append( ", whCode=" + whCode );
		ret.append( ", departmentName=" + departmentName );
		ret.append( ", supplierName=" + supplierName );
		ret.append( ", currency=" + currency );
		ret.append( ", prsremarks=" + prsremarks );
		ret.append( ", roleCode=" + roleCode );
		ret.append( ", status=" + status );
		return ret.toString();
	}

}
