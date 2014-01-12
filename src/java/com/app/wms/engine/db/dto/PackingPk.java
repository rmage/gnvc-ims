package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the packing table.
 */
public class PackingPk implements Serializable
{
	protected String packingNo;

	/** 
	 * Sets the value of packingNo
	 */
	public void setPackingNo(String packingNo)
	{
		this.packingNo = packingNo;
	}

	/** 
	 * Gets the value of packingNo
	 */
	public String getPackingNo()
	{
		return packingNo;
	}

	/**
	 * Method 'PackingPk'
	 * 
	 */
	public PackingPk()
	{
	}

	/**
	 * Method 'PackingPk'
	 * 
	 * @param packingNo
	 */
	public PackingPk(final String packingNo)
	{
		this.packingNo = packingNo;
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
		
		if (!(_other instanceof PackingPk)) {
			return false;
		}
		
		final PackingPk _cast = (PackingPk) _other;
		if (packingNo == null ? _cast.packingNo != packingNo : !packingNo.equals( _cast.packingNo )) {
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
		if (packingNo != null) {
			_hashCode = 29 * _hashCode + packingNo.hashCode();
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
		ret.append( "com.app.wms.engine.dto.PackingPk: " );
		ret.append( "packingNo=" + packingNo );
		return ret.toString();
	}

}
