package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the consolidate table.
 */
public class ConsolidatePk implements Serializable
{
	protected String consolidateNo;

	/** 
	 * Sets the value of consolidateNo
	 */
	public void setConsolidateNo(String consolidateNo)
	{
		this.consolidateNo = consolidateNo;
	}

	/** 
	 * Gets the value of consolidateNo
	 */
	public String getConsolidateNo()
	{
		return consolidateNo;
	}

	/**
	 * Method 'ConsolidatePk'
	 * 
	 */
	public ConsolidatePk()
	{
	}

	/**
	 * Method 'ConsolidatePk'
	 * 
	 * @param consolidateNo
	 */
	public ConsolidatePk(final String consolidateNo)
	{
		this.consolidateNo = consolidateNo;
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
		
		if (!(_other instanceof ConsolidatePk)) {
			return false;
		}
		
		final ConsolidatePk _cast = (ConsolidatePk) _other;
		if (consolidateNo == null ? _cast.consolidateNo != consolidateNo : !consolidateNo.equals( _cast.consolidateNo )) {
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
		if (consolidateNo != null) {
			_hashCode = 29 * _hashCode + consolidateNo.hashCode();
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
		ret.append( "com.app.wms.engine.dto.ConsolidatePk: " );
		ret.append( "consolidateNo=" + consolidateNo );
		return ret.toString();
	}

}
