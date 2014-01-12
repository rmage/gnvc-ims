package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the corp_user_group table.
 */
public class CorpUserGroupPk implements Serializable
{
	protected String corpUsergroupId;

	/** 
	 * Sets the value of corpUsergroupId
	 */
	public void setCorpUsergroupId(String corpUsergroupId)
	{
		this.corpUsergroupId = corpUsergroupId;
	}

	/** 
	 * Gets the value of corpUsergroupId
	 */
	public String getCorpUsergroupId()
	{
		return corpUsergroupId;
	}

	/**
	 * Method 'CorpUserGroupPk'
	 * 
	 */
	public CorpUserGroupPk()
	{
	}

	/**
	 * Method 'CorpUserGroupPk'
	 * 
	 * @param corpUsergroupId
	 */
	public CorpUserGroupPk(final String corpUsergroupId)
	{
		this.corpUsergroupId = corpUsergroupId;
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
		
		if (!(_other instanceof CorpUserGroupPk)) {
			return false;
		}
		
		final CorpUserGroupPk _cast = (CorpUserGroupPk) _other;
		if (corpUsergroupId == null ? _cast.corpUsergroupId != corpUsergroupId : !corpUsergroupId.equals( _cast.corpUsergroupId )) {
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
		if (corpUsergroupId != null) {
			_hashCode = 29 * _hashCode + corpUsergroupId.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.CorpUserGroupPk: " );
		ret.append( "corpUsergroupId=" + corpUsergroupId );
		return ret.toString();
	}

}
