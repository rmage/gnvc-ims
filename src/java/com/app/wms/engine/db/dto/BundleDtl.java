package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class BundleDtl implements Serializable
{
	/** 
	 * This attribute maps to the column BUNDLE_CODE in the BUNDLE_DTL table.
	 */
	protected String bundleCode;

	/** 
	 * This attribute maps to the column ITEM_CODE in the BUNDLE_DTL table.
	 */
	protected String itemCode;

	/** 
	 * This attribute maps to the column QUANTITY in the BUNDLE_DTL table.
	 */
	protected BigDecimal quantity;

	/** 
	 * This attribute maps to the column CREATED_BY in the BUNDLE_DTL table.
	 */
	protected BigDecimal createdBy;

	/** 
	 * This attribute maps to the column CREATED_DATE in the BUNDLE_DTL table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column UPDATED_BY in the BUNDLE_DTL table.
	 */
	protected BigDecimal updatedBy;

	/** 
	 * This attribute maps to the column UPDATED_DATE in the BUNDLE_DTL table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'BundleDtl'
	 * 
	 */
	public BundleDtl()
	{
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
	 * Method 'getItemCode'
	 * 
	 * @return String
	 */
	public String getItemCode()
	{
		return itemCode;
	}

	/**
	 * Method 'setItemCode'
	 * 
	 * @param itemCode
	 */
	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}

	/**
	 * Method 'getQuantity'
	 * 
	 * @return BigDecimal
	 */
	public BigDecimal getQuantity()
	{
		return quantity;
	}

	/**
	 * Method 'setQuantity'
	 * 
	 * @param quantity
	 */
	public void setQuantity(BigDecimal quantity)
	{
		this.quantity = quantity;
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
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}
		
		if (_other == this) {
			return true;
		}
		
		if (!(_other instanceof BundleDtl)) {
			return false;
		}
		
		final BundleDtl _cast = (BundleDtl) _other;
		if (bundleCode == null ? _cast.bundleCode != bundleCode : !bundleCode.equals( _cast.bundleCode )) {
			return false;
		}
		
		if (itemCode == null ? _cast.itemCode != itemCode : !itemCode.equals( _cast.itemCode )) {
			return false;
		}
		
		if (quantity == null ? _cast.quantity != quantity : !quantity.equals( _cast.quantity )) {
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
		
		if (itemCode != null) {
			_hashCode = 29 * _hashCode + itemCode.hashCode();
		}
		
		if (quantity != null) {
			_hashCode = 29 * _hashCode + quantity.hashCode();
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
	 * @return BundleDtlPk
	 */
	public BundleDtlPk createPk()
	{
		return new BundleDtlPk(bundleCode, itemCode);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.BundleDtl: " );
		ret.append( "bundleCode=" + bundleCode );
		ret.append( ", itemCode=" + itemCode );
		ret.append( ", quantity=" + quantity );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
