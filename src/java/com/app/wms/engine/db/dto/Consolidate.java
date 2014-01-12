package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Consolidate implements Serializable
{
	/** 
	 * This attribute maps to the column consolidate_no in the consolidate table.
	 */
	protected int id;
	protected String consolidateNo;

	/** 
	 * This attribute maps to the column consolidate_date in the consolidate table.
	 */
	protected Date consolidateDate;

	/** 
	 * This attribute maps to the column created_by in the consolidate table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the consolidate table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the consolidate table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the consolidate table.
	 */
	protected Date updatedDate;
	
	protected String corpId;
	
	protected String whCode;

	/**
	 * Method 'Consolidate'
	 * 
	 */
	public Consolidate()
	{
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method 'getConsolidateNo'
	 * 
	 * @return String
	 */
	public String getConsolidateNo()
	{
		return consolidateNo;
	}

	/**
	 * Method 'setConsolidateNo'
	 * 
	 * @param consolidateNo
	 */
	public void setConsolidateNo(String consolidateNo)
	{
		this.consolidateNo = consolidateNo;
	}

	/**
	 * Method 'getConsolidateDate'
	 * 
	 * @return Date
	 */
	public Date getConsolidateDate()
	{
		return consolidateDate;
	}

	/**
	 * Method 'setConsolidateDate'
	 * 
	 * @param consolidateDate
	 */
	public void setConsolidateDate(Date consolidateDate)
	{
		this.consolidateDate = consolidateDate;
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
		
		if (!(_other instanceof Consolidate)) {
			return false;
		}
		
		final Consolidate _cast = (Consolidate) _other;
		if (consolidateNo == null ? _cast.consolidateNo != consolidateNo : !consolidateNo.equals( _cast.consolidateNo )) {
			return false;
		}
		
		if (consolidateDate == null ? _cast.consolidateDate != consolidateDate : !consolidateDate.equals( _cast.consolidateDate )) {
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
		if (consolidateNo != null) {
			_hashCode = 29 * _hashCode + consolidateNo.hashCode();
		}
		
		if (consolidateDate != null) {
			_hashCode = 29 * _hashCode + consolidateDate.hashCode();
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
	 * @return ConsolidatePk
	 */
	public ConsolidatePk createPk()
	{
		return new ConsolidatePk(consolidateNo);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.dto.Consolidate: " );
		ret.append( "id=" + id );
		ret.append( "consolidateNo=" + consolidateNo );
		ret.append( ", consolidateDate=" + consolidateDate );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}
