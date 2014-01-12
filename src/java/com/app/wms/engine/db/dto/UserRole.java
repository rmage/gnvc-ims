package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class UserRole implements Serializable
{
	/** 
	 * This attribute maps to the column id in the user_role table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column role_code in the user_role table.
	 */
	protected String roleCode;

	/** 
	 * This attribute maps to the column role_name in the user_role table.
	 */
	protected String roleName;

	/** 
	 * This attribute maps to the column role_level in the user_role table.
	 */
	protected int roleLevel;

	/** 
	 * This attribute represents whether the primitive attribute roleLevel is null.
	 */
	protected boolean roleLevelNull = true;
	
	protected String departmentCode;

	/**
	 * Method 'UserRole'
	 * 
	 */
	public UserRole()
	{
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * Method 'getId'
	 * 
	 * @return int
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Method 'setId'
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Method 'getRoleCode'
	 * 
	 * @return String
	 */
	public String getRoleCode()
	{
		return roleCode;
	}

	/**
	 * Method 'setRoleCode'
	 * 
	 * @param roleCode
	 */
	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	/**
	 * Method 'getRoleName'
	 * 
	 * @return String
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * Method 'setRoleName'
	 * 
	 * @param roleName
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * Method 'getRoleLevel'
	 * 
	 * @return int
	 */
	public int getRoleLevel()
	{
		return roleLevel;
	}

	/**
	 * Method 'setRoleLevel'
	 * 
	 * @param roleLevel
	 */
	public void setRoleLevel(int roleLevel)
	{
		this.roleLevel = roleLevel;
		this.roleLevelNull = false;
	}

	/**
	 * Method 'setRoleLevelNull'
	 * 
	 * @param value
	 */
	public void setRoleLevelNull(boolean value)
	{
		this.roleLevelNull = value;
	}

	/**
	 * Method 'isRoleLevelNull'
	 * 
	 * @return boolean
	 */
	public boolean isRoleLevelNull()
	{
		return roleLevelNull;
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
		
		if (!(_other instanceof UserRole)) {
			return false;
		}
		
		final UserRole _cast = (UserRole) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (roleCode == null ? _cast.roleCode != roleCode : !roleCode.equals( _cast.roleCode )) {
			return false;
		}
		
		if (roleName == null ? _cast.roleName != roleName : !roleName.equals( _cast.roleName )) {
			return false;
		}
		
		if (roleLevel != _cast.roleLevel) {
			return false;
		}
		
		if (roleLevelNull != _cast.roleLevelNull) {
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
		_hashCode = 29 * _hashCode + id;
		if (roleCode != null) {
			_hashCode = 29 * _hashCode + roleCode.hashCode();
		}
		
		if (roleName != null) {
			_hashCode = 29 * _hashCode + roleName.hashCode();
		}
		
		_hashCode = 29 * _hashCode + roleLevel;
		_hashCode = 29 * _hashCode + (roleLevelNull ? 1 : 0);
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return UserRolePk
	 */
	public UserRolePk createPk()
	{
		return new UserRolePk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.UserRole: " );
		ret.append( "id=" + id );
		ret.append( ", roleCode=" + roleCode );
		ret.append( ", roleName=" + roleName );
		ret.append( ", roleLevel=" + roleLevel );
		return ret.toString();
	}

}
