package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the kitting table.
 */
public class KittingPk implements Serializable
{
	protected String kittingNo;

	/** 
	 * Sets the value of kittingNo
	 */
	public void setKittingNo(String kittingNo)
	{
		this.kittingNo = kittingNo;
	}

	/** 
	 * Gets the value of kittingNo
	 */
	public String getKittingNo()
	{
		return kittingNo;
	}

	/**
	 * Method 'KittingPk'
	 * 
	 */
	public KittingPk()
	{
	}

	/**
	 * Method 'KittingPk'
	 * 
	 * @param kittingNo
	 */
	public KittingPk(final String kittingNo)
	{
		this.kittingNo = kittingNo;
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
		
		if (!(_other instanceof KittingPk)) {
			return false;
		}
		
		final KittingPk _cast = (KittingPk) _other;
		if (kittingNo == null ? _cast.kittingNo != kittingNo : !kittingNo.equals( _cast.kittingNo )) {
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
		if (kittingNo != null) {
			_hashCode = 29 * _hashCode + kittingNo.hashCode();
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
		ret.append( "com.app.wms.engine.dto.KittingPk: " );
		ret.append( "kittingNo=" + kittingNo );
		return ret.toString();
	}

}
