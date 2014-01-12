package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppMenuRole implements Serializable
{
	/** 
	 * This attribute maps to the column ROLE_CODE in the APP_MENU_ROLE table.
	 */
	protected String roleCode;

	/** 
	 * This attribute maps to the column MENU_CODE in the APP_MENU_ROLE table.
	 */
	protected String menuCode;

	/** 
	 * This attribute maps to the column IS_VIEW in the APP_MENU_ROLE table.
	 */
	protected String isView;

	/** 
	 * This attribute maps to the column IS_CREATE in the APP_MENU_ROLE table.
	 */
	protected String isCreate;

	/** 
	 * This attribute maps to the column IS_EDIT in the APP_MENU_ROLE table.
	 */
	protected String isEdit;

	/** 
	 * This attribute maps to the column IS_DELETE in the APP_MENU_ROLE table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column CREATED_BY in the APP_MENU_ROLE table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column CREATED_DATE in the APP_MENU_ROLE table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column UPDATED_BY in the APP_MENU_ROLE table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column UPDATED_DATE in the APP_MENU_ROLE table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'AppMenuRole'
	 * 
	 */
	public AppMenuRole()
	{
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
	 * Method 'getMenuCode'
	 * 
	 * @return String
	 */
	public String getMenuCode()
	{
		return menuCode;
	}

	/**
	 * Method 'setMenuCode'
	 * 
	 * @param menuCode
	 */
	public void setMenuCode(String menuCode)
	{
		this.menuCode = menuCode;
	}

	/**
	 * Method 'getIsView'
	 * 
	 * @return String
	 */
	public String getIsView()
	{
		return isView;
	}

	/**
	 * Method 'setIsView'
	 * 
	 * @param isView
	 */
	public void setIsView(String isView)
	{
		this.isView = isView;
	}

	/**
	 * Method 'getIsCreate'
	 * 
	 * @return String
	 */
	public String getIsCreate()
	{
		return isCreate;
	}

	/**
	 * Method 'setIsCreate'
	 * 
	 * @param isCreate
	 */
	public void setIsCreate(String isCreate)
	{
		this.isCreate = isCreate;
	}

	/**
	 * Method 'getIsEdit'
	 * 
	 * @return String
	 */
	public String getIsEdit()
	{
		return isEdit;
	}

	/**
	 * Method 'setIsEdit'
	 * 
	 * @param isEdit
	 */
	public void setIsEdit(String isEdit)
	{
		this.isEdit = isEdit;
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
    @Override
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof AppMenuRole)) {
			return false;
		}
		
		final AppMenuRole _cast = (AppMenuRole) _other;
		if (roleCode == null ? (_cast.roleCode == null ? roleCode != null : !_cast.roleCode.equals(roleCode)) : !roleCode.equals( _cast.roleCode )) {
			return false;
		}
		
		if (menuCode == null ? (_cast.menuCode == null ? menuCode != null : !_cast.menuCode.equals(menuCode)) : !menuCode.equals( _cast.menuCode )) {
			return false;
		}
		
		if (isView == null ? (_cast.isView == null ? isView != null : !_cast.isView.equals(isView)) : !isView.equals( _cast.isView )) {
			return false;
		}
		
		if (isCreate == null ? (_cast.isCreate == null ? isCreate != null : !_cast.isCreate.equals(isCreate)) : !isCreate.equals( _cast.isCreate )) {
			return false;
		}
		
		if (isEdit == null ? (_cast.isEdit == null ? isEdit != null : !_cast.isEdit.equals(isEdit)) : !isEdit.equals( _cast.isEdit )) {
			return false;
		}
		
		if (isDelete == null ? (_cast.isDelete == null ? isDelete != null : !_cast.isDelete.equals(isDelete)) : !isDelete.equals( _cast.isDelete )) {
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
		
		if (isView != null) {
			_hashCode = 29 * _hashCode + isView.hashCode();
		}
		
		if (isCreate != null) {
			_hashCode = 29 * _hashCode + isCreate.hashCode();
		}
		
		if (isEdit != null) {
			_hashCode = 29 * _hashCode + isEdit.hashCode();
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
	 * @return AppMenuRolePk
	 */
	public AppMenuRolePk createPk()
	{
		return new AppMenuRolePk(roleCode, menuCode);
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
		ret.append( "com.app.wms.engine.db.dto.AppMenuRole: " );
		ret.append( "roleCode=" + roleCode );
		ret.append( ", menuCode=" + menuCode );
		ret.append( ", isView=" + isView );
		ret.append( ", isCreate=" + isCreate );
		ret.append( ", isEdit=" + isEdit );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
