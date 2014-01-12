package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class BundleDetail implements Serializable
{
	/** 
	 * This attribute maps to the column bundle_code in the bundle_detail table.
	 */
	protected String bundleCode;

	/** 
	 * This attribute maps to the column product_id in the bundle_detail table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column product_code in the bundle_detail table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the bundle_detail table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column unit_code in the bundle_detail table.
	 */
	protected String unitCode;

	/** 
	 * This attribute maps to the column qty_bundle in the bundle_detail table.
	 */
	protected int qtyBundle;

	/** 
	 * This attribute represents whether the primitive attribute qtyBundle is null.
	 */
	protected boolean qtyBundleNull = true;

	/** 
	 * This attribute maps to the column user_id in the bundle_detail table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the bundle_detail table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the bundle_detail table.
	 */
	protected String whCode;

	/**
	 * Method 'BundleDetail'
	 * 
	 */
	public BundleDetail()
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
	 * Method 'getProductId'
	 * 
	 * @return String
	 */
	public String getProductId()
	{
		return productId;
	}

	/**
	 * Method 'setProductId'
	 * 
	 * @param productId
	 */
	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	/**
	 * Method 'getProductCode'
	 * 
	 * @return String
	 */
	public String getProductCode()
	{
		return productCode;
	}

	/**
	 * Method 'setProductCode'
	 * 
	 * @param productCode
	 */
	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	/**
	 * Method 'getProductName'
	 * 
	 * @return String
	 */
	public String getProductName()
	{
		return productName;
	}

	/**
	 * Method 'setProductName'
	 * 
	 * @param productName
	 */
	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	/**
	 * Method 'getUnitCode'
	 * 
	 * @return String
	 */
	public String getUnitCode()
	{
		return unitCode;
	}

	/**
	 * Method 'setUnitCode'
	 * 
	 * @param unitCode
	 */
	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}

	/**
	 * Method 'getQtyBundle'
	 * 
	 * @return int
	 */
	public int getQtyBundle()
	{
		return qtyBundle;
	}

	/**
	 * Method 'setQtyBundle'
	 * 
	 * @param qtyBundle
	 */
	public void setQtyBundle(int qtyBundle)
	{
		this.qtyBundle = qtyBundle;
		this.qtyBundleNull = false;
	}

	/**
	 * Method 'setQtyBundleNull'
	 * 
	 * @param value
	 */
	public void setQtyBundleNull(boolean value)
	{
		this.qtyBundleNull = value;
	}

	/**
	 * Method 'isQtyBundleNull'
	 * 
	 * @return boolean
	 */
	public boolean isQtyBundleNull()
	{
		return qtyBundleNull;
	}

	/**
	 * Method 'getUserId'
	 * 
	 * @return String
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Method 'setUserId'
	 * 
	 * @param userId
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
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
		
		if (!(_other instanceof BundleDetail)) {
			return false;
		}
		
		final BundleDetail _cast = (BundleDetail) _other;
		if (bundleCode == null ? _cast.bundleCode != bundleCode : !bundleCode.equals( _cast.bundleCode )) {
			return false;
		}
		
		if (productId == null ? _cast.productId != productId : !productId.equals( _cast.productId )) {
			return false;
		}
		
		if (productCode == null ? _cast.productCode != productCode : !productCode.equals( _cast.productCode )) {
			return false;
		}
		
		if (productName == null ? _cast.productName != productName : !productName.equals( _cast.productName )) {
			return false;
		}
		
		if (unitCode == null ? _cast.unitCode != unitCode : !unitCode.equals( _cast.unitCode )) {
			return false;
		}
		
		if (qtyBundle != _cast.qtyBundle) {
			return false;
		}
		
		if (qtyBundleNull != _cast.qtyBundleNull) {
			return false;
		}
		
		if (userId == null ? _cast.userId != userId : !userId.equals( _cast.userId )) {
			return false;
		}
		
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
			return false;
		}
		
		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
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
		
		if (productId != null) {
			_hashCode = 29 * _hashCode + productId.hashCode();
		}
		
		if (productCode != null) {
			_hashCode = 29 * _hashCode + productCode.hashCode();
		}
		
		if (productName != null) {
			_hashCode = 29 * _hashCode + productName.hashCode();
		}
		
		if (unitCode != null) {
			_hashCode = 29 * _hashCode + unitCode.hashCode();
		}
		
		_hashCode = 29 * _hashCode + qtyBundle;
		_hashCode = 29 * _hashCode + (qtyBundleNull ? 1 : 0);
		if (userId != null) {
			_hashCode = 29 * _hashCode + userId.hashCode();
		}
		
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
		}
		
		if (whCode != null) {
			_hashCode = 29 * _hashCode + whCode.hashCode();
		}
		
		return _hashCode;
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.BundleDetail: " );
		ret.append( "bundleCode=" + bundleCode );
		ret.append( ", productId=" + productId );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", unitCode=" + unitCode );
		ret.append( ", qtyBundle=" + qtyBundle );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}
