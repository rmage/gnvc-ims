package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the corp_user table.
 */
public class CorpUserPk implements Serializable
{
	protected String corpuserId;

	/** 
	 * Sets the value of corpuserId
	 */
	public void setCorpuserId(String corpuserId)
	{
		this.corpuserId = corpuserId;
	}

	/** 
	 * Gets the value of corpuserId
	 */
	public String getCorpuserId()
	{
		return corpuserId;
	}

	/**
	 * Method 'CorpUserPk'
	 * 
	 */
	public CorpUserPk()
	{
	}

	/**
	 * Method 'CorpUserPk'
	 * 
	 * @param corpuserId
	 */
	public CorpUserPk(final String corpuserId)
	{
		this.corpuserId = corpuserId;
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
		
		if (!(_other instanceof CorpUserPk)) {
			return false;
		}
		
		final CorpUserPk _cast = (CorpUserPk) _other;
		if (corpuserId == null ? _cast.corpuserId != corpuserId : !corpuserId.equals( _cast.corpuserId )) {
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
		if (corpuserId != null) {
			_hashCode = 29 * _hashCode + corpuserId.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.CorpUserPk: " );
		ret.append( "corpuserId=" + corpuserId );
		return ret.toString();
	}

}
