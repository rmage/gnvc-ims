package com.app.wms.engine.db.dto;

import java.io.Serializable;

/** 
 * This class represents the primary key of the APP_MENU table.
 */
public class AppMenuGroupPk implements Serializable
{
	protected String groupCode;

	/** 
	 * Sets the value of groupCode
	 */
	public void setGroupCode(String groupCode)
	{
		this.groupCode = groupCode;
	}

	/** 
	 * Gets the value of groupCode
	 */
	public String getGroupCode()
	{
		return groupCode;
	}

	/**
	 * Method 'AppMenuPk'
	 * 
	 */
	public AppMenuGroupPk()
	{
	}

	/**
	 * Method 'AppMenuPk'
	 * 
	 * @param groupCode
	 */
	public AppMenuGroupPk(final String groupCode)
	{
		this.groupCode = groupCode;
	}

	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
    @Override
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof AppMenuGroupPk)) {
			return false;
		}
		
		final AppMenuGroupPk _cast = (AppMenuGroupPk) _other;
		if (groupCode == null ? (_cast.groupCode == null ? groupCode != null : !_cast.groupCode.equals(groupCode)) : !groupCode.equals( _cast.groupCode )) {
			return false;
		}
		
		return true;
	}

	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
    @Override
	public int hashCode()
	{
		int _hashCode = 0;
		if (groupCode != null) {
			_hashCode = 29 * _hashCode + groupCode.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
    @Override
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.AppMenuPk: " );
		ret.append( "groupCode=" + groupCode );
		return ret.toString();
	}

}
