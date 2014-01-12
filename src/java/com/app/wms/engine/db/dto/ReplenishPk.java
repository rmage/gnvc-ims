package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class ReplenishPk implements Serializable 
{

	protected String ReplenishCode;

	/** 
	 * Sets the value of ReplenishCode
	 */
	public void setReplenishCode(String ReplenishCode)
	{
		this.ReplenishCode = ReplenishCode;
	}

	/** 
	 * Gets the value of ReplenishCode
	 */
	public String getReplenishCode()
	{
		return ReplenishCode;
	}

	/**
	 * Method 'ReplenishPk'
	 * 
	 */
	public ReplenishPk()
	{
	}

	/**
	 * Method 'ReplenishPk'
	 * 
	 * @param ReplenishCode
	 */
	public ReplenishPk(final String ReplenishCode)
	{
		this.ReplenishCode = ReplenishCode;
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
		
		if (!(_other instanceof ReplenishPk)) {
			return false;
		}
		
		final ReplenishPk _cast = (ReplenishPk) _other;
		if (ReplenishCode == null ? _cast.ReplenishCode != ReplenishCode : !ReplenishCode.equals( _cast.ReplenishCode )) {
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
		if (ReplenishCode != null) {
			_hashCode = 29 * _hashCode + ReplenishCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.ReplenishPk: " );
		ret.append( "ReplenishCode=" + ReplenishCode );
		return ret.toString();
	}



}
