package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the corp table.
 */
public class CorpPk implements Serializable
{
	protected String corpId;

	/** 
	 * Sets the value of corpId
	 */
	public void setCorpId(String corpId)
	{
		this.corpId = corpId;
	}

	/** 
	 * Gets the value of corpId
	 */
	public String getCorpId()
	{
		return corpId;
	}

	/**
	 * Method 'CorpPk'
	 * 
	 */
	public CorpPk()
	{
	}

	/**
	 * Method 'CorpPk'
	 * 
	 * @param corpId
	 */
	public CorpPk(final String corpId)
	{
		this.corpId = corpId;
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
		
		if (!(_other instanceof CorpPk)) {
			return false;
		}
		
		final CorpPk _cast = (CorpPk) _other;
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
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
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.CorpPk: " );
		ret.append( "corpId=" + corpId );
		return ret.toString();
	}

}
