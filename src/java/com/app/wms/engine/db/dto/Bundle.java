package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Bundle implements Serializable
{
	/** 
	 * This attribute maps to the column bundle_code in the bundle table.
	 */
	protected String bundleCode;

	/** 
	 * This attribute maps to the column bundle_name in the bundle table.
	 */
	protected String bundleName;

	/** 
	 * This attribute maps to the column created_by in the bundle table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the bundle table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the bundle table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the bundle table.
	 */
	protected Date updatedDate;
	
	protected String userId;
	
	protected String corpId;
	
	protected String whCode;

	/**
	 * Method 'Bundle'
	 * 
	 */
	public Bundle()
	{
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	/**
	 * Method 'getBundleCode'
	 * 
	 * @return String
	 */
	public String getBundleCode()
	{
		return bundleCode;
	}

	/**
	 * Method 'setBundleCode'
	 * 
	 * @param bundleCode
	 */
	public void setBundleCode(String bundleCode)
	{
		this.bundleCode = bundleCode;
	}

	/**
	 * Method 'getBundleName'
	 * 
	 * @return String
	 */
	public String getBundleName()
	{
		return bundleName;
	}

	/**
	 * Method 'setBundleName'
	 * 
	 * @param bundleName
	 */
	public void setBundleName(String bundleName)
	{
		this.bundleName = bundleName;
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
		
		if (!(_other instanceof Bundle)) {
			return false;
		}
		
		final Bundle _cast = (Bundle) _other;
		if (bundleCode == null ? _cast.bundleCode != bundleCode : !bundleCode.equals( _cast.bundleCode )) {
			return false;
		}
		
		if (bundleName == null ? _cast.bundleName != bundleName : !bundleName.equals( _cast.bundleName )) {
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
		if (bundleCode != null) {
			_hashCode = 29 * _hashCode + bundleCode.hashCode();
		}
		
		if (bundleName != null) {
			_hashCode = 29 * _hashCode + bundleName.hashCode();
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
	 * @return BundlePk
	 */
	public BundlePk createPk()
	{
		return new BundlePk(bundleCode);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Bundle: " );
		ret.append( "bundleCode=" + bundleCode );
		ret.append( ", bundleName=" + bundleName );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
