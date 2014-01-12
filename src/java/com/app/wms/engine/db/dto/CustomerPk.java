package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the CUSTOMER table.
 */
public class CustomerPk implements Serializable
{
	protected BigDecimal customerId;

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
	 * Method 'CustomerPk'
	 * 
	 */
	public CustomerPk()
	{
	}

	/**
	 * Method 'CustomerPk'
	 * 
	 * @param customerId
	 */
	public CustomerPk(final BigDecimal customerId)
	{
		this.customerId = customerId;
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
		
		if (!(_other instanceof CustomerPk)) {
			return false;
		}
		
		final CustomerPk _cast = (CustomerPk) _other;
		if (customerId == null ? _cast.customerId != customerId : !customerId.equals( _cast.customerId )) {
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
		ret.append( "com.app.wms.engine.db.dto.CustomerPk: " );
		ret.append( "customerId=" + customerId );
		return ret.toString();
	}

}
