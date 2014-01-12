package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the USER table.
 */
public class UserPk implements Serializable
{
	protected String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserPk()
	{
	}

	/**
	 * Method 'UserPk'
	 * 
	 * @param userId
	 */
	public UserPk(final String userId)
	{
		this.userId = userId;
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
		
		if (!(_other instanceof UserPk)) {
			return false;
		}
		
		final UserPk _cast = (UserPk) _other;
		if (userId == null ? _cast.userId != userId : !userId.equals( _cast.userId )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return String
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (userId != null) {
			_hashCode = 29 * _hashCode + userId.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.UserPk: " );
		ret.append( "userId=" + userId );
		return ret.toString();
	}

}
