package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class ApprovalRange implements Serializable
{
	/** 
	 * This attribute maps to the column id in the approval_range table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column username in the approval_range table.
	 */
	protected String username;

	/** 
	 * This attribute maps to the column role_code in the approval_range table.
	 */
	protected String roleCode;

	/** 
	 * This attribute maps to the column val_amount in the approval_range table.
	 */
	//protected float valAmount;
	protected BigDecimal fromAmount;
	
	protected BigDecimal toAmount;
	
	/** 
	 * This attribute represents whether the primitive attribute valAmount is null.
	 */
	protected boolean valAmountNull = true;

	/** 
	 * This attribute maps to the column is_active in the approval_range table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the approval_range table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the approval_range table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the approval_range table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the approval_range table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the approval_range table.
	 */
	protected Date updatedDate;
	
	protected UserRole userRole;

	/**
	 * Method 'ApprovalRange'
	 * 
	 */
	public ApprovalRange()
	{
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
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
	 * Method 'getUsername'
	 * 
	 * @return String
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * Method 'setUsername'
	 * 
	 * @param username
	 */
	public void setUsername(String username)
	{
		this.username = username;
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
	 * Method 'getValAmount'
	 * 
	 * @return float
	 */
//	public float getValAmount()
//	{
//		return valAmount;
//	}

	/**
	 * Method 'setValAmount'
	 * 
	 * @param valAmount
	 */
//	public void setValAmount(float valAmount)
//	{
//		this.valAmount = valAmount;
//		this.valAmountNull = false;
//	}

	/**
	 * Method 'setValAmountNull'
	 * 
	 * @param value
	 */
	public void setValAmountNull(boolean value)
	{
		this.valAmountNull = value;
	}

	/**
	 * Method 'isValAmountNull'
	 * 
	 * @return boolean
	 */
	public boolean isValAmountNull()
	{
		return valAmountNull;
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
		
		if (!(_other instanceof ApprovalRange)) {
			return false;
		}
		
		final ApprovalRange _cast = (ApprovalRange) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (username == null ? _cast.username != username : !username.equals( _cast.username )) {
			return false;
		}
		
		if (roleCode == null ? _cast.roleCode != roleCode : !roleCode.equals( _cast.roleCode )) {
			return false;
		}
		
		if (valAmountNull != _cast.valAmountNull) {
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
		_hashCode = 29 * _hashCode + id;
		if (username != null) {
			_hashCode = 29 * _hashCode + username.hashCode();
		}
		
		if (roleCode != null) {
			_hashCode = 29 * _hashCode + roleCode.hashCode();
		}
		
//		_hashCode = 29 * _hashCode + Float.floatToIntBits(valAmount);
		_hashCode = 29 * _hashCode + (valAmountNull ? 1 : 0);
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
	 * @return ApprovalRangePk
	 */
	public ApprovalRangePk createPk()
	{
		return new ApprovalRangePk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.ApprovalRange: " );
		ret.append( "id=" + id );
		ret.append( ", username=" + username );
		ret.append( ", roleCode=" + roleCode );
		ret.append( ", fromAmount=" + fromAmount );
		ret.append( ", toAmount=" + toAmount );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
