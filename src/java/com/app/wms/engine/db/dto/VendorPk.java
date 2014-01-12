package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the VENDOR table.
 */
public class VendorPk implements Serializable
{
	protected String vendorCode;

	/** 
	 * Sets the value of vendorCode
	 */
	public void setVendorCode(String vendorCode)
	{
		this.vendorCode = vendorCode;
	}

	/** 
	 * Gets the value of vendorCode
	 */
	public String getVendorCode()
	{
		return vendorCode;
	}

	/**
	 * Method 'VendorPk'
	 * 
	 */
	public VendorPk()
	{
	}

	/**
	 * Method 'VendorPk'
	 * 
	 * @param vendorCode
	 */
	public VendorPk(final String vendorCode)
	{
		this.vendorCode = vendorCode;
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
		
		if (!(_other instanceof VendorPk)) {
			return false;
		}
		
		final VendorPk _cast = (VendorPk) _other;
		if (vendorCode == null ? _cast.vendorCode != vendorCode : !vendorCode.equals( _cast.vendorCode )) {
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
		if (vendorCode != null) {
			_hashCode = 29 * _hashCode + vendorCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.VendorPk: " );
		ret.append( "vendorCode=" + vendorCode );
		return ret.toString();
	}

}
