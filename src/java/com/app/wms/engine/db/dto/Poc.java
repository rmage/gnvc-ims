package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class Poc implements Serializable
{
	/** 
	 * This attribute maps to the column POC_CODE in the POC table.
	 */
	protected String pocCode;

	/** 
	 * This attribute maps to the column NAME in the POC table.
	 */
	protected String name;

	/** 
	 * This attribute maps to the column CREATED_BY in the POC table.
	 */
	protected BigDecimal createdBy;

	/** 
	 * This attribute maps to the column CREATED_DATE in the POC table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column UPDATED_BY in the POC table.
	 */
	protected BigDecimal updatedBy;

	/** 
	 * This attribute maps to the column UPDATED_DATE in the POC table.
	 */
	protected Date updatedDate;


        /**
	 * This attribute maps to the column IS_ACTIVE in the POC table.
	 */
	protected String isActive;


	/**
	 * Method 'Poc'
	 * 
	 */
	public Poc()
	{
	}

	/**
	 * Method 'getPocCode'
	 * 
	 * @return String
	 */
	public String getPocCode()
	{
		return pocCode;
	}

	/**
	 * Method 'setPocCode'
	 * 
	 * @param pocCode
	 */
	public void setPocCode(String pocCode)
	{
		this.pocCode = pocCode;
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
	 * Method 'getCreatedBy'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * Method 'setCreatedBy'
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(BigDecimal createdBy)
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
	 * @return BigDecimal
	 */
	public BigDecimal getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * Method 'setUpdatedBy'
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(BigDecimal updatedBy)
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

	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof Poc)) {
			return false;
		}
		
		final Poc _cast = (Poc) _other;
		if (pocCode == null ? _cast.pocCode != pocCode : !pocCode.equals( _cast.pocCode )) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
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
                
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
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
		if (pocCode != null) {
			_hashCode = 29 * _hashCode + pocCode.hashCode();
		}
		
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
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

                if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return PocPk
	 */
	public PocPk createPk()
	{
		return new PocPk(pocCode);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Poc: " );
		ret.append( "pocCode=" + pocCode );
		ret.append( ", name=" + name );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
                ret.append( ", isActive=" + isActive );
		return ret.toString();
	}

}
