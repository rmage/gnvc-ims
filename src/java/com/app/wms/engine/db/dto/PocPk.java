package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the POC table.
 */
public class PocPk implements Serializable
{
	protected String pocCode;

	/** 
	 * Sets the value of pocCode
	 */
	public void setPocCode(String pocCode)
	{
		this.pocCode = pocCode;
	}

	/** 
	 * Gets the value of pocCode
	 */
	public String getPocCode()
	{
		return pocCode;
	}

	/**
	 * Method 'PocPk'
	 * 
	 */
	public PocPk()
	{
	}

	/**
	 * Method 'PocPk'
	 * 
	 * @param pocCode
	 */
	public PocPk(final String pocCode)
	{
		this.pocCode = pocCode;
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
		
		if (!(_other instanceof PocPk)) {
			return false;
		}
		
		final PocPk _cast = (PocPk) _other;
		if (pocCode == null ? _cast.pocCode != pocCode : !pocCode.equals( _cast.pocCode )) {
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
		if (pocCode != null) {
			_hashCode = 29 * _hashCode + pocCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.PocPk: " );
		ret.append( "pocCode=" + pocCode );
		return ret.toString();
	}

}
