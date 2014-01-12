package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class PurchasePk implements Serializable 
{

	protected String PurchaseCode;

	/** 
	 * Sets the value of PurchaseCode
	 */
	public void setPurchaseCode(String PurchaseCode)
	{
		this.PurchaseCode = PurchaseCode;
	}

	/** 
	 * Gets the value of PurchaseCode
	 */
	public String getPurchaseCode()
	{
		return PurchaseCode;
	}

	/**
	 * Method 'PurchasePk'
	 * 
	 */
	public PurchasePk()
	{
	}

	/**
	 * Method 'PurchasePk'
	 * 
	 * @param PurchaseCode
	 */
	public PurchasePk(final String PurchaseCode)
	{
		this.PurchaseCode = PurchaseCode;
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
		
		if (!(_other instanceof PurchasePk)) {
			return false;
		}
		
		final PurchasePk _cast = (PurchasePk) _other;
		if (PurchaseCode == null ? _cast.PurchaseCode != PurchaseCode : !PurchaseCode.equals( _cast.PurchaseCode )) {
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
		if (PurchaseCode != null) {
			_hashCode = 29 * _hashCode + PurchaseCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.PurchasePk: " );
		ret.append( "PurchaseCode=" + PurchaseCode );
		return ret.toString();
	}



}
