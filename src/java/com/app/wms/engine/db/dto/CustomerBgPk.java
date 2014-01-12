package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the CUSTOMER_BG table.
 */
public class CustomerBgPk implements Serializable
{
	protected BigDecimal customerId;

	protected String bgCode;

	/** 
	 * Sets the value of customerId
	 */
	public void setCustomerId(BigDecimal customerId)
	{
		this.customerId = customerId;
	}

	/** 
	 * Gets the value of customerId
	 */
	public BigDecimal getCustomerId()
	{
		return customerId;
	}

	/** 
	 * Sets the value of bgCode
	 */
	public void setBgCode(String bgCode)
	{
		this.bgCode = bgCode;
	}

	/** 
	 * Gets the value of bgCode
	 */
	public String getBgCode()
	{
		return bgCode;
	}

	/**
	 * Method 'CustomerBgPk'
	 * 
	 */
	public CustomerBgPk()
	{
	}

	/**
	 * Method 'CustomerBgPk'
	 * 
	 * @param customerId
	 * @param bgCode
	 */
	public CustomerBgPk(final BigDecimal customerId, final String bgCode)
	{
		this.customerId = customerId;
		this.bgCode = bgCode;
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
		
		if (!(_other instanceof CustomerBgPk)) {
			return false;
		}
		
		final CustomerBgPk _cast = (CustomerBgPk) _other;
		if (customerId == null ? _cast.customerId != customerId : !customerId.equals( _cast.customerId )) {
			return false;
		}
		
		if (bgCode == null ? _cast.bgCode != bgCode : !bgCode.equals( _cast.bgCode )) {
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
		
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.CustomerBgPk: " );
		ret.append( "customerId=" + customerId );
		ret.append( ", bgCode=" + bgCode );
		return ret.toString();
	}

}
