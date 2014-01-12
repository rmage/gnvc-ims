package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Replenishment implements Serializable
{
	/** 
	 * This attribute maps to the column replenish_no in the replenishment table.
	 */
	protected String replenishNo;

	/** 
	 * This attribute maps to the column replenish_date in the replenishment table.
	 */
	protected Date replenishDate;

	/** 
	 * This attribute maps to the column status_app in the replenishment table.
	 */
	protected String statusApp;

	/** 
	 * This attribute maps to the column app_date in the replenishment table.
	 */
	protected Date appDate;

	/** 
	 * This attribute maps to the column created_by in the replenishment table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the replenishment table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the replenishment table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the replenishment table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Replenishment'
	 * 
	 */
	public Replenishment()
	{
	}

	/**
	 * Method 'getReplenishNo'
	 * 
	 * @return String
	 */
	public String getReplenishNo()
	{
		return replenishNo;
	}

	/**
	 * Method 'setReplenishNo'
	 * 
	 * @param replenishNo
	 */
	public void setReplenishNo(String replenishNo)
	{
		this.replenishNo = replenishNo;
	}

	/**
	 * Method 'getReplenishDate'
	 * 
	 * @return Date
	 */
	public Date getReplenishDate()
	{
		return replenishDate;
	}

	/**
	 * Method 'setReplenishDate'
	 * 
	 * @param replenishDate
	 */
	public void setReplenishDate(Date replenishDate)
	{
		this.replenishDate = replenishDate;
	}

	/**
	 * Method 'getStatusApp'
	 * 
	 * @return String
	 */
	public String getStatusApp()
	{
		return statusApp;
	}

	/**
	 * Method 'setStatusApp'
	 * 
	 * @param statusApp
	 */
	public void setStatusApp(String statusApp)
	{
		this.statusApp = statusApp;
	}

	/**
	 * Method 'getAppDate'
	 * 
	 * @return Date
	 */
	public Date getAppDate()
	{
		return appDate;
	}

	/**
	 * Method 'setAppDate'
	 * 
	 * @param appDate
	 */
	public void setAppDate(Date appDate)
	{
		this.appDate = appDate;
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
		
		if (!(_other instanceof Replenishment)) {
			return false;
		}
		
		final Replenishment _cast = (Replenishment) _other;
		if (replenishNo == null ? _cast.replenishNo != replenishNo : !replenishNo.equals( _cast.replenishNo )) {
			return false;
		}
		
		if (replenishDate == null ? _cast.replenishDate != replenishDate : !replenishDate.equals( _cast.replenishDate )) {
			return false;
		}
		
		if (statusApp == null ? _cast.statusApp != statusApp : !statusApp.equals( _cast.statusApp )) {
			return false;
		}
		
		if (appDate == null ? _cast.appDate != appDate : !appDate.equals( _cast.appDate )) {
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
		if (replenishNo != null) {
			_hashCode = 29 * _hashCode + replenishNo.hashCode();
		}
		
		if (replenishDate != null) {
			_hashCode = 29 * _hashCode + replenishDate.hashCode();
		}
		
		if (statusApp != null) {
			_hashCode = 29 * _hashCode + statusApp.hashCode();
		}
		
		if (appDate != null) {
			_hashCode = 29 * _hashCode + appDate.hashCode();
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
	 * @return ReplenishmentPk
	 */
	public ReplenishmentPk createPk()
	{
		return new ReplenishmentPk(replenishNo);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Replenishment: " );
		ret.append( "replenishNo=" + replenishNo );
		ret.append( ", replenishDate=" + replenishDate );
		ret.append( ", statusApp=" + statusApp );
		ret.append( ", appDate=" + appDate );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
