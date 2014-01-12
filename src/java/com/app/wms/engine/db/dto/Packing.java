package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Packing implements Serializable
{
	protected int id;
	/** 
	 * This attribute maps to the column packing_no in the packing table.
	 */
	protected String packingNo;

	/** 
	 * This attribute maps to the column packing_date in the packing table.
	 */
	protected Date packingDate;

	/** 
	 * This attribute maps to the column created_by in the packing table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the packing table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the packing table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the packing table.
	 */
	protected Date updatedDate;
	
	protected String corpId;
	
	protected String whCode;

	/**
	 * Method 'Packing'
	 * 
	 */
	public Packing()
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
	 * Method 'getPackingNo'
	 * 
	 * @return String
	 */
	public String getPackingNo()
	{
		return packingNo;
	}

	/**
	 * Method 'setPackingNo'
	 * 
	 * @param packingNo
	 */
	public void setPackingNo(String packingNo)
	{
		this.packingNo = packingNo;
	}

	/**
	 * Method 'getPackingDate'
	 * 
	 * @return Date
	 */
	public Date getPackingDate()
	{
		return packingDate;
	}

	/**
	 * Method 'setPackingDate'
	 * 
	 * @param packingDate
	 */
	public void setPackingDate(Date packingDate)
	{
		this.packingDate = packingDate;
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
		
		if (!(_other instanceof Packing)) {
			return false;
		}
		
		final Packing _cast = (Packing) _other;
		if (packingNo == null ? _cast.packingNo != packingNo : !packingNo.equals( _cast.packingNo )) {
			return false;
		}
		
		if (packingDate == null ? _cast.packingDate != packingDate : !packingDate.equals( _cast.packingDate )) {
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
		if (packingNo != null) {
			_hashCode = 29 * _hashCode + packingNo.hashCode();
		}
		
		if (packingDate != null) {
			_hashCode = 29 * _hashCode + packingDate.hashCode();
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
	 * @return PackingPk
	 */
	public PackingPk createPk()
	{
		return new PackingPk(packingNo);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.dto.Packing: " );
		ret.append( "id=" + id );
		ret.append( ",packingNo=" + packingNo );
		ret.append( ", packingDate=" + packingDate );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}
