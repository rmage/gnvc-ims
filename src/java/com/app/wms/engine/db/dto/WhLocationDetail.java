package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;

public class WhLocationDetail implements Serializable
{
	/** 
	 * This attribute maps to the column id in the wh_location_detail table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column location_id in the wh_location_detail table.
	 */
	protected String locationId;

	/** 
	 * This attribute maps to the column product_id in the wh_location_detail table.
	 */
	protected String productId;

	/** 
	 * This attribute maps to the column product_code in the wh_location_detail table.
	 */
	protected String productCode;

	/** 
	 * This attribute maps to the column product_name in the wh_location_detail table.
	 */
	protected String productName;

	/** 
	 * This attribute maps to the column unit_code in the wh_location_detail table.
	 */
	protected String unitCode;

	/** 
	 * This attribute maps to the column product_category in the wh_location_detail table.
	 */
	protected String productCategory;

	/** 
	 * This attribute maps to the column user_id in the wh_location_detail table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the wh_location_detail table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the wh_location_detail table.
	 */
	protected String whCode;

	/**
	 * Method 'WhLocationDetail'
	 * 
	 */
	public WhLocationDetail()
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
	 * Method 'getLocationId'
	 * 
	 * @return String
	 */
	public String getLocationId()
	{
		return locationId;
	}

	/**
	 * Method 'setLocationId'
	 * 
	 * @param locationId
	 */
	public void setLocationId(String locationId)
	{
		this.locationId = locationId;
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
	 * Method 'getProductCategory'
	 * 
	 * @return String
	 */
	public String getProductCategory()
	{
		return productCategory;
	}

	/**
	 * Method 'setProductCategory'
	 * 
	 * @param productCategory
	 */
	public void setProductCategory(String productCategory)
	{
		this.productCategory = productCategory;
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
		
		if (!(_other instanceof WhLocationDetail)) {
			return false;
		}
		
		final WhLocationDetail _cast = (WhLocationDetail) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (locationId == null ? _cast.locationId != locationId : !locationId.equals( _cast.locationId )) {
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
		
		if (productCategory == null ? _cast.productCategory != productCategory : !productCategory.equals( _cast.productCategory )) {
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
		_hashCode = 29 * _hashCode + id;
		if (locationId != null) {
			_hashCode = 29 * _hashCode + locationId.hashCode();
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
		
		if (productCategory != null) {
			_hashCode = 29 * _hashCode + productCategory.hashCode();
		}
		
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
	 * Method 'createPk'
	 * 
	 * @return WhLocationDetailPk
	 */
	public WhLocationDetailPk createPk()
	{
		return new WhLocationDetailPk(locationId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.WhLocationDetail: " );
		ret.append( "id=" + id );
		ret.append( ", locationId=" + locationId );
		ret.append( ", productId=" + productId );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", unitCode=" + unitCode );
		ret.append( ", productCategory=" + productCategory );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		return ret.toString();
	}

}
