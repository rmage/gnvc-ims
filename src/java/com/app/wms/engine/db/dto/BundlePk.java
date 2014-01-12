package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the bundle table.
 */
public class BundlePk implements Serializable
{
	protected String bundleCode;

	/** 
	 * Sets the value of bundleCode
	 */
	public void setBundleCode(String bundleCode)
	{
		this.bundleCode = bundleCode;
	}

	/** 
	 * Gets the value of bundleCode
	 */
	public String getBundleCode()
	{
		return bundleCode;
	}

	/**
	 * Method 'BundlePk'
	 * 
	 */
	public BundlePk()
	{
	}

	/**
	 * Method 'BundlePk'
	 * 
	 * @param bundleCode
	 */
	public BundlePk(final String bundleCode)
	{
		this.bundleCode = bundleCode;
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
		
		if (!(_other instanceof BundlePk)) {
			return false;
		}
		
		final BundlePk _cast = (BundlePk) _other;
		if (bundleCode == null ? _cast.bundleCode != bundleCode : !bundleCode.equals( _cast.bundleCode )) {
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
		if (bundleCode != null) {
			_hashCode = 29 * _hashCode + bundleCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.BundlePk: " );
		ret.append( "bundleCode=" + bundleCode );
		return ret.toString();
	}

}
