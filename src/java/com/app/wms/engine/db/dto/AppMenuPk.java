package com.app.wms.engine.db.dto;

import java.io.Serializable;

/** 
 * This class represents the primary key of the APP_MENU table.
 */
public class AppMenuPk implements Serializable
{
	protected String menuCode;

	/** 
	 * Sets the value of menuCode
	 */
	public void setMenuCode(String menuCode)
	{
		this.menuCode = menuCode;
	}

	/** 
	 * Gets the value of menuCode
	 */
	public String getMenuCode()
	{
		return menuCode;
	}

	/**
	 * Method 'AppMenuPk'
	 * 
	 */
	public AppMenuPk()
	{
	}

	/**
	 * Method 'AppMenuPk'
	 * 
	 * @param menuCode
	 */
	public AppMenuPk(final String menuCode)
	{
		this.menuCode = menuCode;
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
		
		if (!(_other instanceof AppMenuPk)) {
			return false;
		}
		
		final AppMenuPk _cast = (AppMenuPk) _other;
		if (menuCode == null ? (_cast.menuCode == null ? menuCode != null : !_cast.menuCode.equals(menuCode)) : !menuCode.equals( _cast.menuCode )) {
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
		if (menuCode != null) {
			_hashCode = 29 * _hashCode + menuCode.hashCode();
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
		ret.append( ".db.dto.AppMenuPk: " );
		ret.append( "menuCode=" + menuCode );
		return ret.toString();
	}

}
