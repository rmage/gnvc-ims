package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class CustomerBg implements Serializable
{
	/** 
	 * This attribute maps to the column CUSTOMER_ID in the CUSTOMER_BG table.
	 */
	protected BigDecimal customerId;

	/** 
	 * This attribute maps to the column BG_CODE in the CUSTOMER_BG table.
	 */
	protected String bgCode;

	/** 
	 * This attribute maps to the column CREATED_BY in the CUSTOMER_BG table.
	 */
	protected BigDecimal createdBy;

	/** 
	 * This attribute maps to the column CREATED_DATE in the CUSTOMER_BG table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column UPDATED_BY in the CUSTOMER_BG table.
	 */
	protected BigDecimal updatedBy;

	/** 
	 * This attribute maps to the column UPDATED_DATE in the CUSTOMER_BG table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'CustomerBg'
	 * 
	 */
	public CustomerBg()
	{
	}

	/**
	 * Method 'getCustomerId'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCustomerId()
	{
		return customerId;
	}

	/**
	 * Method 'setCustomerId'
	 * 
	 * @param customerId
	 */
	public void setCustomerId(BigDecimal customerId)
	{
		this.customerId = customerId;
	}

	/**
	 * Method 'getBgCode'
	 * 
	 * @return String
	 */
	public String getBgCode()
	{
		return bgCode;
	}

	/**
	 * Method 'setBgCode'
	 * 
	 * @param bgCode
	 */
	public void setBgCode(String bgCode)
	{
		this.bgCode = bgCode;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(BigDecimal createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * Method 'getCreatedDate'
	 * 
	 * @return Date
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * Method 'setCreatedDate'
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * Method 'getUpdatedBy'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(BigDecimal updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * Method 'getUpdatedDate'
	 * 
	 * @return Date
	 */
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * Method 'setUpdatedDate'
	 * 
	 * @param updatedDate
	 */
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
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
		
		if (!(_other instanceof CustomerBg)) {
			return false;
		}
		
		final CustomerBg _cast = (CustomerBg) _other;
		if (customerId == null ? _cast.customerId != customerId : !customerId.equals( _cast.customerId )) {
			return false;
		}
		
		if (bgCode == null ? _cast.bgCode != bgCode : !bgCode.equals( _cast.bgCode )) {
			return false;
		}
		
		if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals( _cast.createdBy )) {
			return false;
		}
		
		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}
		
		if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals( _cast.updatedBy )) {
			return false;
		}
		
		if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals( _cast.updatedDate )) {
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
		if (customerId != null) {
			_hashCode = 29 * _hashCode + customerId.hashCode();
		}
		
		if (bgCode != null) {
			_hashCode = 29 * _hashCode + bgCode.hashCode();
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
	 * @return CustomerBgPk
	 */
	public CustomerBgPk createPk()
	{
		return new CustomerBgPk(customerId, bgCode);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.CustomerBg: " );
		ret.append( "customerId=" + customerId );
		ret.append( ", bgCode=" + bgCode );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
