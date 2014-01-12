package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Tallyman implements Serializable
{
	/** 
	 * This attribute maps to the column id in the tallyman table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column tally_id in the tallyman table.
	 */
	protected String tallyId;

	/** 
	 * This attribute maps to the column code in the tallyman table.
	 */
	protected String code;

	/** 
	 * This attribute maps to the column name in the tallyman table.
	 */
	protected String name;

	/** 
	 * This attribute maps to the column tallyname in the tallyman table.
	 */
	protected String tallyname;

	/** 
	 * This attribute maps to the column jobfunction in the tallyman table.
	 */
	protected String jobfunction;

	/** 
	 * This attribute maps to the column corp_id in the tallyman table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the tallyman table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column is_active in the tallyman table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the tallyman table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the tallyman table.
	 */
	protected int createdBy;

	/** 
	 * This attribute represents whether the primitive attribute createdBy is null.
	 */
	protected boolean createdByNull = true;

	/** 
	 * This attribute maps to the column created_date in the tallyman table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the tallyman table.
	 */
	protected int updatedBy;

	/** 
	 * This attribute represents whether the primitive attribute updatedBy is null.
	 */
	protected boolean updatedByNull = true;

	/** 
	 * This attribute maps to the column updated_date in the tallyman table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Tallyman'
	 * 
	 */
	public Tallyman()
	{
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
	 * Method 'getTallyId'
	 * 
	 * @return String
	 */
	public String getTallyId()
	{
		return tallyId;
	}

	/**
	 * Method 'setTallyId'
	 * 
	 * @param tallyId
	 */
	public void setTallyId(String tallyId)
	{
		this.tallyId = tallyId;
	}

	/**
	 * Method 'getCode'
	 * 
	 * @return String
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Method 'setCode'
	 * 
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * Method 'getName'
	 * 
	 * @return String
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Method 'setName'
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Method 'getTallyname'
	 * 
	 * @return String
	 */
	public String getTallyname()
	{
		return tallyname;
	}

	/**
	 * Method 'setTallyname'
	 * 
	 * @param tallyname
	 */
	public void setTallyname(String tallyname)
	{
		this.tallyname = tallyname;
	}

	/**
	 * Method 'getJobfunction'
	 * 
	 * @return String
	 */
	public String getJobfunction()
	{
		return jobfunction;
	}

	/**
	 * Method 'setJobfunction'
	 * 
	 * @param jobfunction
	 */
	public void setJobfunction(String jobfunction)
	{
		this.jobfunction = jobfunction;
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
	 * @return int
	 */
	public int getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(int createdBy)
	{
		this.createdBy = createdBy;
		this.createdByNull = false;
	}

	/**
	 * Method 'setCreatedByNull'
	 * 
	 * @param value
	 */
	public void setCreatedByNull(boolean value)
	{
		this.createdByNull = value;
	}

	/**
	 * Method 'isCreatedByNull'
	 * 
	 * @return boolean
	 */
	public boolean isCreatedByNull()
	{
		return createdByNull;
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
	 * @return int
	 */
	public int getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(int updatedBy)
	{
		this.updatedBy = updatedBy;
		this.updatedByNull = false;
	}

	/**
	 * Method 'setUpdatedByNull'
	 * 
	 * @param value
	 */
	public void setUpdatedByNull(boolean value)
	{
		this.updatedByNull = value;
	}

	/**
	 * Method 'isUpdatedByNull'
	 * 
	 * @return boolean
	 */
	public boolean isUpdatedByNull()
	{
		return updatedByNull;
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
		
		if (!(_other instanceof Tallyman)) {
			return false;
		}
		
		final Tallyman _cast = (Tallyman) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (tallyId == null ? _cast.tallyId != tallyId : !tallyId.equals( _cast.tallyId )) {
			return false;
		}
		
		if (code == null ? _cast.code != code : !code.equals( _cast.code )) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
			return false;
		}
		
		if (tallyname == null ? _cast.tallyname != tallyname : !tallyname.equals( _cast.tallyname )) {
			return false;
		}
		
		if (jobfunction == null ? _cast.jobfunction != jobfunction : !jobfunction.equals( _cast.jobfunction )) {
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
		
		if (createdBy != _cast.createdBy) {
			return false;
		}
		
		if (createdByNull != _cast.createdByNull) {
			return false;
		}
		
		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}
		
		if (updatedBy != _cast.updatedBy) {
			return false;
		}
		
		if (updatedByNull != _cast.updatedByNull) {
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
		if (tallyId != null) {
			_hashCode = 29 * _hashCode + tallyId.hashCode();
		}
		
		if (code != null) {
			_hashCode = 29 * _hashCode + code.hashCode();
		}
		
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
		}
		
		if (tallyname != null) {
			_hashCode = 29 * _hashCode + tallyname.hashCode();
		}
		
		if (jobfunction != null) {
			_hashCode = 29 * _hashCode + jobfunction.hashCode();
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
		
		_hashCode = 29 * _hashCode + createdBy;
		_hashCode = 29 * _hashCode + (createdByNull ? 1 : 0);
		if (createdDate != null) {
			_hashCode = 29 * _hashCode + createdDate.hashCode();
		}
		
		_hashCode = 29 * _hashCode + updatedBy;
		_hashCode = 29 * _hashCode + (updatedByNull ? 1 : 0);
		if (updatedDate != null) {
			_hashCode = 29 * _hashCode + updatedDate.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return TallymanPk
	 */
	public TallymanPk createPk()
	{
		return new TallymanPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Tallyman: " );
		ret.append( "id=" + id );
		ret.append( ", tallyId=" + tallyId );
		ret.append( ", code=" + code );
		ret.append( ", name=" + name );
		ret.append( ", tallyname=" + tallyname );
		ret.append( ", jobfunction=" + jobfunction );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
