package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Currency implements Serializable
{
	/** 
	 * This attribute maps to the column id in the currency table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column currency_code in the currency table.
	 */
	protected String currencyCode;

	/** 
	 * This attribute maps to the column currency_name in the currency table.
	 */
	protected String currencyName;

	/** 
	 * This attribute maps to the column currency_symbol in the currency table.
	 */
	protected String currencySymbol;

	/** 
	 * This attribute maps to the column is_active in the currency table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the currency table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the currency table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the currency table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the currency table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the currency table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Currency'
	 * 
	 */
	public Currency()
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
	 * Method 'getCurrencyCode'
	 * 
	 * @return String
	 */
	public String getCurrencyCode()
	{
		return currencyCode;
	}

	/**
	 * Method 'setCurrencyCode'
	 * 
	 * @param currencyCode
	 */
	public void setCurrencyCode(String currencyCode)
	{
		this.currencyCode = currencyCode;
	}

	/**
	 * Method 'getCurrencyName'
	 * 
	 * @return String
	 */
	public String getCurrencyName()
	{
		return currencyName;
	}

	/**
	 * Method 'setCurrencyName'
	 * 
	 * @param currencyName
	 */
	public void setCurrencyName(String currencyName)
	{
		this.currencyName = currencyName;
	}

	/**
	 * Method 'getCurrencySymbol'
	 * 
	 * @return String
	 */
	public String getCurrencySymbol()
	{
		return currencySymbol;
	}

	/**
	 * Method 'setCurrencySymbol'
	 * 
	 * @param currencySymbol
	 */
	public void setCurrencySymbol(String currencySymbol)
	{
		this.currencySymbol = currencySymbol;
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
		
		if (!(_other instanceof Currency)) {
			return false;
		}
		
		final Currency _cast = (Currency) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (currencyCode == null ? _cast.currencyCode != currencyCode : !currencyCode.equals( _cast.currencyCode )) {
			return false;
		}
		
		if (currencyName == null ? _cast.currencyName != currencyName : !currencyName.equals( _cast.currencyName )) {
			return false;
		}
		
		if (currencySymbol == null ? _cast.currencySymbol != currencySymbol : !currencySymbol.equals( _cast.currencySymbol )) {
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
		if (currencyCode != null) {
			_hashCode = 29 * _hashCode + currencyCode.hashCode();
		}
		
		if (currencyName != null) {
			_hashCode = 29 * _hashCode + currencyName.hashCode();
		}
		
		if (currencySymbol != null) {
			_hashCode = 29 * _hashCode + currencySymbol.hashCode();
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
	 * @return CurrencyPk
	 */
	public CurrencyPk createPk()
	{
		return new CurrencyPk(id);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Currency: " );
		ret.append( "id=" + id );
		ret.append( ", currencyCode=" + currencyCode );
		ret.append( ", currencyName=" + currencyName );
		ret.append( ", currencySymbol=" + currencySymbol );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
