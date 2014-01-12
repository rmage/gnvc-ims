package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the SALES_STATUS table.
 */
public class SalesStatusPk implements Serializable
{
	protected String statusCode;

	/** 
	 * Sets the value of statusCode
	 */
	public void setStatusCode(String statusCode)
	{
		this.statusCode = statusCode;
	}

	/** 
	 * Gets the value of statusCode
	 */
	public String getStatusCode()
	{
		return statusCode;
	}

	/**
	 * Method 'SalesStatusPk'
	 * 
	 */
	public SalesStatusPk()
	{
	}

	/**
	 * Method 'SalesStatusPk'
	 * 
	 * @param statusCode
	 */
	public SalesStatusPk(final String statusCode)
	{
		this.statusCode = statusCode;
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
		
		if (!(_other instanceof SalesStatusPk)) {
			return false;
		}
		
		final SalesStatusPk _cast = (SalesStatusPk) _other;
		if (statusCode == null ? _cast.statusCode != statusCode : !statusCode.equals( _cast.statusCode )) {
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
		if (statusCode != null) {
			_hashCode = 29 * _hashCode + statusCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.SalesStatusPk: " );
		ret.append( "statusCode=" + statusCode );
		return ret.toString();
	}

}
