package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Purchase implements Serializable 
{

	/** 
	 * This attribute maps to the column Purchase_CODE in the Purchase table.
	 */
	protected String PurchaseCode;

	/** 
	 * This attribute maps to the column NAME in the Purchase table.
	 */
	protected String name;
	
	/** 
	 * This attribute maps to the column IS_ACTIVE in the Purchase table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column CREATED_BY in the Purchase table.
	 */
	protected BigDecimal createdBy;

	/** 
	 * This attribute maps to the column CREATED_DATE in the Purchase table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column UPDATED_BY in the Purchase table.
	 */
	protected BigDecimal updatedBy;

	/** 
	 * This attribute maps to the column UPDATED_DATE in the Purchase table.
	 */
	protected Date updatedDate;


	

	/**
	 * Method 'Purchase'
	 * 
	 */
	public Purchase()
	{
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Method 'getPurchaseCode'
	 * 
	 * @return String
	 */
	public String getPurchaseCode()
	{
		return PurchaseCode;
	}

	/**
	 * Method 'setPurchaseCode'
	 * 
	 * @param PurchaseCode
	 */
	public void setPurchaseCode(String PurchaseCode)
	{
		this.PurchaseCode = PurchaseCode;
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
		
		if (!(_other instanceof Purchase)) {
			return false;
		}
		
		final Purchase _cast = (Purchase) _other;
		if (PurchaseCode == null ? _cast.PurchaseCode != PurchaseCode : !PurchaseCode.equals( _cast.PurchaseCode )) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
//		if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals( _cast.createdBy )) {
//			return false;
//		}
		
		if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals( _cast.createdDate )) {
			return false;
		}
		
//		if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals( _cast.updatedBy )) {
//			return false;
//		}
		
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
		if (PurchaseCode != null) {
			_hashCode = 29 * _hashCode + PurchaseCode.hashCode();
		}
		
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
		}
		
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
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
	 * @return PurchasePk
	 */
	public PurchasePk createPk()
	{
		return new PurchasePk(PurchaseCode);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Purchase: " );
		ret.append( "PurchaseCode=" + PurchaseCode );
		ret.append( ", name=" + name );
		ret.append( ", isActive=" + isActive );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}



}
