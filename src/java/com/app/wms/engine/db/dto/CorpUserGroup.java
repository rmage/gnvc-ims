package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class CorpUserGroup implements Serializable
{
	/** 
	 * This attribute maps to the column corp_usergroup_id in the corp_user_group table.
	 */
	protected String corpUsergroupId;

	/** 
	 * This attribute maps to the column corp_id in the corp_user_group table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the corp_user_group table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column role_code in the corp_user_group table.
	 */
	protected String roleCode;

	/** 
	 * This attribute maps to the column is_active in the corp_user_group table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the corp_user_group table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the corp_user_group table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the corp_user_group table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the corp_user_group table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the corp_user_group table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'CorpUserGroup'
	 * 
	 */
	public CorpUserGroup()
	{
	}

	/**
	 * Method 'getCorpUsergroupId'
	 * 
	 * @return String
	 */
	public String getCorpUsergroupId()
	{
		return corpUsergroupId;
	}

	/**
	 * Method 'setCorpUsergroupId'
	 * 
	 * @param corpUsergroupId
	 */
	public void setCorpUsergroupId(String corpUsergroupId)
	{
		this.corpUsergroupId = corpUsergroupId;
	}

	/**
	 * Method 'getCorpId'
	 * 
	 * @return String
	 */
	public String getCorpId()
	{
		return corpId;
	}

	/**
	 * Method 'setCorpId'
	 * 
	 * @param corpId
	 */
	public void setCorpId(String corpId)
	{
		this.corpId = corpId;
	}

	/**
	 * Method 'getWhCode'
	 * 
	 * @return String
	 */
	public String getWhCode()
	{
		return whCode;
	}

	/**
	 * Method 'setWhCode'
	 * 
	 * @param whCode
	 */
	public void setWhCode(String whCode)
	{
		this.whCode = whCode;
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
	 * Method 'getIsActive'
	 * 
	 * @return String
	 */
	public String getIsActive()
	{
		return isActive;
	}

	/**
	 * Method 'setIsActive'
	 * 
	 * @param isActive
	 */
	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	/**
	 * Method 'getIsDelete'
	 * 
	 * @return String
	 */
	public String getIsDelete()
	{
		return isDelete;
	}

	/**
	 * Method 'setIsDelete'
	 * 
	 * @param isDelete
	 */
	public void setIsDelete(String isDelete)
	{
		this.isDelete = isDelete;
	}

	/**
	 * Method 'getCreatedBy'
	 * 
	 * @return String
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * Method 'getCreatedDate'
	 * 
	 * @return Date
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * Method 'setCreatedDate'
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * Method 'getUpdatedBy'
	 * 
	 * @return String
	 */
	public String getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * Method 'getUpdatedDate'
	 * 
	 * @return Date
	 */
	public Date getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * Method 'setUpdatedDate'
	 * 
	 * @param updatedDate
	 */
	public void setUpdatedDate(Date updatedDate)
	{
		this.updatedDate = updatedDate;
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
		
		if (!(_other instanceof CorpUserGroup)) {
			return false;
		}
		
		final CorpUserGroup _cast = (CorpUserGroup) _other;
		if (corpUsergroupId == null ? _cast.corpUsergroupId != corpUsergroupId : !corpUsergroupId.equals( _cast.corpUsergroupId )) {
			return false;
		}
		
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
			return false;
		}
		
		if (roleCode == null ? _cast.roleCode != roleCode : !roleCode.equals( _cast.roleCode )) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
		if (isDelete == null ? _cast.isDelete != isDelete : !isDelete.equals( _cast.isDelete )) {
			return false;
		}
		
		if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals( _cast.createdBy )) {
			return false;
		}
		
		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}
		
		if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals( _cast.updatedBy )) {
			return false;
		}
		
		if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals( _cast.updatedDate )) {
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
		
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		if (roleCode != null) {
			_hashCode = 29 * _hashCode + roleCode.hashCode();
		}
		
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		if (isDelete != null) {
			_hashCode = 29 * _hashCode + isDelete.hashCode();
		}
		
		if (createdBy != null) {
			_hashCode = 29 * _hashCode + createdBy.hashCode();
		}
		
		if (createdDate != null) {
			_hashCode = 29 * _hashCode + createdDate.hashCode();
		}
		
		if (updatedBy != null) {
			_hashCode = 29 * _hashCode + updatedBy.hashCode();
		}
		
		if (updatedDate != null) {
			_hashCode = 29 * _hashCode + updatedDate.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return CorpUserGroupPk
	 */
	public CorpUserGroupPk createPk()
	{
		return new CorpUserGroupPk(corpUsergroupId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.CorpUserGroup: " );
		ret.append( "corpUsergroupId=" + corpUsergroupId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		ret.append( ", roleCode=" + roleCode );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
