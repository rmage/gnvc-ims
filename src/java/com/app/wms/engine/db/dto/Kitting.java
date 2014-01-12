package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Kitting implements Serializable
{
	protected int id;
	/** 
	 * This attribute maps to the column kitting_no in the kitting table.
	 */
	protected String kittingNo;

	/** 
	 * This attribute maps to the column kitting_date in the kitting table.
	 */
	protected Date kittingDate;

	/** 
	 * This attribute maps to the column created_by in the kitting table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the kitting table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the kitting table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the kitting table.
	 */
	protected Date updatedDate;
	
	protected String corpId;
	
	protected String whCode;

	/**
	 * Method 'Kitting'
	 * 
	 */
	public Kitting()
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
	 * Method 'getKittingNo'
	 * 
	 * @return String
	 */
	public String getKittingNo()
	{
		return kittingNo;
	}

	/**
	 * Method 'setKittingNo'
	 * 
	 * @param kittingNo
	 */
	public void setKittingNo(String kittingNo)
	{
		this.kittingNo = kittingNo;
	}

	/**
	 * Method 'getKittingDate'
	 * 
	 * @return Date
	 */
	public Date getKittingDate()
	{
		return kittingDate;
	}

	/**
	 * Method 'setKittingDate'
	 * 
	 * @param kittingDate
	 */
	public void setKittingDate(Date kittingDate)
	{
		this.kittingDate = kittingDate;
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
		
		if (!(_other instanceof Kitting)) {
			return false;
		}
		
		final Kitting _cast = (Kitting) _other;
		if (kittingNo == null ? _cast.kittingNo != kittingNo : !kittingNo.equals( _cast.kittingNo )) {
			return false;
		}
		
		if (kittingDate == null ? _cast.kittingDate != kittingDate : !kittingDate.equals( _cast.kittingDate )) {
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
		if (kittingNo != null) {
			_hashCode = 29 * _hashCode + kittingNo.hashCode();
		}
		
		if (kittingDate != null) {
			_hashCode = 29 * _hashCode + kittingDate.hashCode();
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
	 * @return KittingPk
	 */
	public KittingPk createPk()
	{
		return new KittingPk(kittingNo);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.dto.Kitting: " );
		ret.append( "id=" + id );
		ret.append( ",kittingNo=" + kittingNo );
		ret.append( ", kittingDate=" + kittingDate );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}
