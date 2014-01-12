package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Consignee implements Serializable
{
	/** 
	 * This attribute maps to the column id in the consignee table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column consignee_code in the consignee table.
	 */
	protected String consigneeCode;

	/** 
	 * This attribute maps to the column consignee_name in the consignee table.
	 */
	protected String consigneeName;

	/** 
	 * This attribute maps to the column address1 in the consignee table.
	 */
	protected String address1;

	/** 
	 * This attribute maps to the column address2 in the consignee table.
	 */
	protected String address2;

	/** 
	 * This attribute maps to the column address3 in the consignee table.
	 */
	protected String address3;

	/** 
	 * This attribute maps to the column consignee_phone in the consignee table.
	 */
	protected String consigneePhone;

	/** 
	 * This attribute maps to the column user_id in the consignee table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the consignee table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the consignee table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column is_active in the consignee table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the consignee table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the consignee table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the consignee table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the consignee table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the consignee table.
	 */
	protected Date updatedDate;
	
	protected String consigneePIC;

	/**
	 * Method 'Consignee'
	 * 
	 */
	public Consignee()
	{
	}

	public String getConsigneePIC() {
		return consigneePIC;
	}

	public void setConsigneePIC(String consigneePIC) {
		this.consigneePIC = consigneePIC;
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
	 * Method 'getConsigneeCode'
	 * 
	 * @return String
	 */
	public String getConsigneeCode()
	{
		return consigneeCode;
	}

	/**
	 * Method 'setConsigneeCode'
	 * 
	 * @param consigneeCode
	 */
	public void setConsigneeCode(String consigneeCode)
	{
		this.consigneeCode = consigneeCode;
	}

	/**
	 * Method 'getConsigneeName'
	 * 
	 * @return String
	 */
	public String getConsigneeName()
	{
		return consigneeName;
	}

	/**
	 * Method 'setConsigneeName'
	 * 
	 * @param consigneeName
	 */
	public void setConsigneeName(String consigneeName)
	{
		this.consigneeName = consigneeName;
	}

	/**
	 * Method 'getAddress1'
	 * 
	 * @return String
	 */
	public String getAddress1()
	{
		return address1;
	}

	/**
	 * Method 'setAddress1'
	 * 
	 * @param address1
	 */
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	/**
	 * Method 'getAddress2'
	 * 
	 * @return String
	 */
	public String getAddress2()
	{
		return address2;
	}

	/**
	 * Method 'setAddress2'
	 * 
	 * @param address2
	 */
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	/**
	 * Method 'getAddress3'
	 * 
	 * @return String
	 */
	public String getAddress3()
	{
		return address3;
	}

	/**
	 * Method 'setAddress3'
	 * 
	 * @param address3
	 */
	public void setAddress3(String address3)
	{
		this.address3 = address3;
	}

	/**
	 * Method 'getConsigneePhone'
	 * 
	 * @return String
	 */
	public String getConsigneePhone()
	{
		return consigneePhone;
	}

	/**
	 * Method 'setConsigneePhone'
	 * 
	 * @param consigneePhone
	 */
	public void setConsigneePhone(String consigneePhone)
	{
		this.consigneePhone = consigneePhone;
	}

	/**
	 * Method 'getUserId'
	 * 
	 * @return String
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Method 'setUserId'
	 * 
	 * @param userId
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
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
		
		if (!(_other instanceof Consignee)) {
			return false;
		}
		
		final Consignee _cast = (Consignee) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (consigneeCode == null ? _cast.consigneeCode != consigneeCode : !consigneeCode.equals( _cast.consigneeCode )) {
			return false;
		}
		
		if (consigneeName == null ? _cast.consigneeName != consigneeName : !consigneeName.equals( _cast.consigneeName )) {
			return false;
		}
		
		if (address1 == null ? _cast.address1 != address1 : !address1.equals( _cast.address1 )) {
			return false;
		}
		
		if (address2 == null ? _cast.address2 != address2 : !address2.equals( _cast.address2 )) {
			return false;
		}
		
		if (address3 == null ? _cast.address3 != address3 : !address3.equals( _cast.address3 )) {
			return false;
		}
		
		if (consigneePhone == null ? _cast.consigneePhone != consigneePhone : !consigneePhone.equals( _cast.consigneePhone )) {
			return false;
		}
		
		if (userId == null ? _cast.userId != userId : !userId.equals( _cast.userId )) {
			return false;
		}
		
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
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
		if (consigneeCode != null) {
			_hashCode = 29 * _hashCode + consigneeCode.hashCode();
		}
		
		if (consigneeName != null) {
			_hashCode = 29 * _hashCode + consigneeName.hashCode();
		}
		
		if (address1 != null) {
			_hashCode = 29 * _hashCode + address1.hashCode();
		}
		
		if (address2 != null) {
			_hashCode = 29 * _hashCode + address2.hashCode();
		}
		
		if (address3 != null) {
			_hashCode = 29 * _hashCode + address3.hashCode();
		}
		
		if (consigneePhone != null) {
			_hashCode = 29 * _hashCode + consigneePhone.hashCode();
		}
		
		if (userId != null) {
			_hashCode = 29 * _hashCode + userId.hashCode();
		}
		
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
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
	 * @return ConsigneePk
	 */
	public ConsigneePk createPk()
	{
		return new ConsigneePk(consigneeCode);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Consignee: " );
		ret.append( "id=" + id );
		ret.append( ", consigneeCode=" + consigneeCode );
		ret.append( ", consigneeName=" + consigneeName );
		ret.append( ", address1=" + address1 );
		ret.append( ", address2=" + address2 );
		ret.append( ", address3=" + address3 );
		ret.append( ", consigneePhone=" + consigneePhone );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		ret.append( ", consigneePIC=" + consigneePIC );
		return ret.toString();
	}

}
