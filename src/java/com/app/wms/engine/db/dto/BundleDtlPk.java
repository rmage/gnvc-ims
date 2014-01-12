package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the BUNDLE_DTL table.
 */
public class BundleDtlPk implements Serializable
{
	protected String bundleCode;

	protected String itemCode;

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
	 * Sets the value of itemCode
	 */
	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}

	/** 
	 * Gets the value of itemCode
	 */
	public String getItemCode()
	{
		return itemCode;
	}

	/**
	 * Method 'BundleDtlPk'
	 * 
	 */
	public BundleDtlPk()
	{
	}

	/**
	 * Method 'BundleDtlPk'
	 * 
	 * @param bundleCode
	 * @param itemCode
	 */
	public BundleDtlPk(final String bundleCode, final String itemCode)
	{
		this.bundleCode = bundleCode;
		this.itemCode = itemCode;
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
		
		if (!(_other instanceof BundleDtlPk)) {
			return false;
		}
		
		final BundleDtlPk _cast = (BundleDtlPk) _other;
		if (bundleCode == null ? _cast.bundleCode != bundleCode : !bundleCode.equals( _cast.bundleCode )) {
			return false;
		}
		
		if (itemCode == null ? _cast.itemCode != itemCode : !itemCode.equals( _cast.itemCode )) {
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
		
		if (itemCode != null) {
			_hashCode = 29 * _hashCode + itemCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.BundleDtlPk: " );
		ret.append( "bundleCode=" + bundleCode );
		ret.append( ", itemCode=" + itemCode );
		return ret.toString();
	}

}
