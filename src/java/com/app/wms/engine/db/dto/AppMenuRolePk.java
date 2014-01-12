package com.app.wms.engine.db.dto;

import java.io.Serializable;

/** 
 * This class represents the primary key of the APP_MENU_ROLE table.
 */
public class AppMenuRolePk implements Serializable
{
	protected String roleCode;

	protected String menuCode;

	/** 
	 * Sets the value of roleCode
	 */
	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	/** 
	 * Gets the value of roleCode
	 */
	public String getRoleCode()
	{
		return roleCode;
	}

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
	 * Method 'AppMenuRolePk'
	 * 
	 */
	public AppMenuRolePk()
	{
	}

	/**
	 * Method 'AppMenuRolePk'
	 * 
	 * @param roleCode
	 * @param menuCode
	 */
	public AppMenuRolePk(final String roleCode, final String menuCode)
	{
		this.roleCode = roleCode;
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
		
		if (!(_other instanceof AppMenuRolePk)) {
			return false;
		}
		
		final AppMenuRolePk _cast = (AppMenuRolePk) _other;
		if (roleCode == null ? (_cast.roleCode == null ? roleCode != null : !_cast.roleCode.equals(roleCode)) : !roleCode.equals( _cast.roleCode )) {
			return false;
		}
		
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
		if (roleCode != null) {
			_hashCode = 29 * _hashCode + roleCode.hashCode();
		}
		
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
		ret.append( "com.app.wms.engine.db.dto.AppMenuRolePk: " );
		ret.append( "roleCode=" + roleCode );
		ret.append( ", menuCode=" + menuCode );
		return ret.toString();
	}

}
