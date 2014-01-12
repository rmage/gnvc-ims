package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class WhLocation implements Serializable
{
	/** 
	 * This attribute maps to the column id in the wh_location table.
	 */
	protected int id;

	/** 
	 * This attribute maps to the column location_id in the wh_location table.
	 */
	protected String locationId;

	/** 
	 * This attribute maps to the column location_code in the wh_location table.
	 */
	protected String locationCode;

	/** 
	 * This attribute maps to the column location_name in the wh_location table.
	 */
	protected String locationName;

	/** 
	 * This attribute maps to the column location_type in the wh_location table.
	 */
	protected String locationType;

	/** 
	 * This attribute maps to the column min_product in the wh_location table.
	 */
	protected int minProduct;

	/** 
	 * This attribute represents whether the primitive attribute minProduct is null.
	 */
	protected boolean minProductNull = true;

	/** 
	 * This attribute maps to the column max_product in the wh_location table.
	 */
	protected int maxProduct;

	/** 
	 * This attribute represents whether the primitive attribute maxProduct is null.
	 */
	protected boolean maxProductNull = true;

	/** 
	 * This attribute maps to the column locating_area in the wh_location table.
	 */
	protected String locatingArea;

	/** 
	 * This attribute maps to the column locating_zone in the wh_location table.
	 */
	protected String locatingZone;

	/** 
	 * This attribute maps to the column location_bay in the wh_location table.
	 */
	protected String locationBay;

	/** 
	 * This attribute maps to the column location_level in the wh_location table.
	 */
	protected String locationLevel;

	/** 
	 * This attribute maps to the column location_position in the wh_location table.
	 */
	protected String locationPosition;

	/** 
	 * This attribute maps to the column allocation_zone in the wh_location table.
	 */
	protected String allocationZone;

	/** 
	 * This attribute maps to the column work_zone in the wh_location table.
	 */
	protected String workZone;

	/** 
	 * This attribute maps to the column is_active in the wh_location table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the wh_location table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column user_id in the wh_location table.
	 */
	protected String userId;

	/** 
	 * This attribute maps to the column corp_id in the wh_location table.
	 */
	protected String corpId;

	/** 
	 * This attribute maps to the column wh_code in the wh_location table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column created_by in the wh_location table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the wh_location table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the wh_location table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the wh_location table.
	 */
	protected Date updatedDate;
	
	protected String productCode;
	protected String productName;
	protected String unitCode;

	/**
	 * Method 'WhLocation'
	 * 
	 */
	protected WhLocationDetail whLocationDetail;
	
	public WhLocation()
	{
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public WhLocationDetail getWhLocationDetail() {
		return whLocationDetail;
	}

	public void setWhLocationDetail(WhLocationDetail whLocationDetail) {
		this.whLocationDetail = whLocationDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	 * Method 'getLocationCode'
	 * 
	 * @return String
	 */
	public String getLocationCode()
	{
		return locationCode;
	}

	/**
	 * Method 'setLocationCode'
	 * 
	 * @param locationCode
	 */
	public void setLocationCode(String locationCode)
	{
		this.locationCode = locationCode;
	}

	/**
	 * Method 'getLocationName'
	 * 
	 * @return String
	 */
	public String getLocationName()
	{
		return locationName;
	}

	/**
	 * Method 'setLocationName'
	 * 
	 * @param locationName
	 */
	public void setLocationName(String locationName)
	{
		this.locationName = locationName;
	}

	/**
	 * Method 'getLocationType'
	 * 
	 * @return String
	 */
	public String getLocationType()
	{
		return locationType;
	}

	/**
	 * Method 'setLocationType'
	 * 
	 * @param locationType
	 */
	public void setLocationType(String locationType)
	{
		this.locationType = locationType;
	}

	/**
	 * Method 'getMinProduct'
	 * 
	 * @return int
	 */
	public int getMinProduct()
	{
		return minProduct;
	}

	/**
	 * Method 'setMinProduct'
	 * 
	 * @param minProduct
	 */
	public void setMinProduct(int minProduct)
	{
		this.minProduct = minProduct;
		this.minProductNull = false;
	}

	/**
	 * Method 'setMinProductNull'
	 * 
	 * @param value
	 */
	public void setMinProductNull(boolean value)
	{
		this.minProductNull = value;
	}

	/**
	 * Method 'isMinProductNull'
	 * 
	 * @return boolean
	 */
	public boolean isMinProductNull()
	{
		return minProductNull;
	}

	/**
	 * Method 'getMaxProduct'
	 * 
	 * @return int
	 */
	public int getMaxProduct()
	{
		return maxProduct;
	}

	/**
	 * Method 'setMaxProduct'
	 * 
	 * @param maxProduct
	 */
	public void setMaxProduct(int maxProduct)
	{
		this.maxProduct = maxProduct;
		this.maxProductNull = false;
	}

	/**
	 * Method 'setMaxProductNull'
	 * 
	 * @param value
	 */
	public void setMaxProductNull(boolean value)
	{
		this.maxProductNull = value;
	}

	/**
	 * Method 'isMaxProductNull'
	 * 
	 * @return boolean
	 */
	public boolean isMaxProductNull()
	{
		return maxProductNull;
	}

	/**
	 * Method 'getLocatingArea'
	 * 
	 * @return String
	 */
	public String getLocatingArea()
	{
		return locatingArea;
	}

	/**
	 * Method 'setLocatingArea'
	 * 
	 * @param locatingArea
	 */
	public void setLocatingArea(String locatingArea)
	{
		this.locatingArea = locatingArea;
	}

	/**
	 * Method 'getLocatingZone'
	 * 
	 * @return String
	 */
	public String getLocatingZone()
	{
		return locatingZone;
	}

	/**
	 * Method 'setLocatingZone'
	 * 
	 * @param locatingZone
	 */
	public void setLocatingZone(String locatingZone)
	{
		this.locatingZone = locatingZone;
	}

	/**
	 * Method 'getLocationBay'
	 * 
	 * @return String
	 */
	public String getLocationBay()
	{
		return locationBay;
	}

	/**
	 * Method 'setLocationBay'
	 * 
	 * @param locationBay
	 */
	public void setLocationBay(String locationBay)
	{
		this.locationBay = locationBay;
	}

	/**
	 * Method 'getLocationLevel'
	 * 
	 * @return String
	 */
	public String getLocationLevel()
	{
		return locationLevel;
	}

	/**
	 * Method 'setLocationLevel'
	 * 
	 * @param locationLevel
	 */
	public void setLocationLevel(String locationLevel)
	{
		this.locationLevel = locationLevel;
	}

	/**
	 * Method 'getLocationPosition'
	 * 
	 * @return String
	 */
	public String getLocationPosition()
	{
		return locationPosition;
	}

	/**
	 * Method 'setLocationPosition'
	 * 
	 * @param locationPosition
	 */
	public void setLocationPosition(String locationPosition)
	{
		this.locationPosition = locationPosition;
	}

	/**
	 * Method 'getAllocationZone'
	 * 
	 * @return String
	 */
	public String getAllocationZone()
	{
		return allocationZone;
	}

	/**
	 * Method 'setAllocationZone'
	 * 
	 * @param allocationZone
	 */
	public void setAllocationZone(String allocationZone)
	{
		this.allocationZone = allocationZone;
	}

	/**
	 * Method 'getWorkZone'
	 * 
	 * @return String
	 */
	public String getWorkZone()
	{
		return workZone;
	}

	/**
	 * Method 'setWorkZone'
	 * 
	 * @param workZone
	 */
	public void setWorkZone(String workZone)
	{
		this.workZone = workZone;
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
		
		if (!(_other instanceof WhLocation)) {
			return false;
		}
		
		final WhLocation _cast = (WhLocation) _other;
		if (id != _cast.id) {
			return false;
		}
		
		if (locationId == null ? _cast.locationId != locationId : !locationId.equals( _cast.locationId )) {
			return false;
		}
		
		if (locationCode == null ? _cast.locationCode != locationCode : !locationCode.equals( _cast.locationCode )) {
			return false;
		}
		
		if (locationName == null ? _cast.locationName != locationName : !locationName.equals( _cast.locationName )) {
			return false;
		}
		
		if (locationType == null ? _cast.locationType != locationType : !locationType.equals( _cast.locationType )) {
			return false;
		}
		
		if (minProduct != _cast.minProduct) {
			return false;
		}
		
		if (minProductNull != _cast.minProductNull) {
			return false;
		}
		
		if (maxProduct != _cast.maxProduct) {
			return false;
		}
		
		if (maxProductNull != _cast.maxProductNull) {
			return false;
		}
		
		if (locatingArea == null ? _cast.locatingArea != locatingArea : !locatingArea.equals( _cast.locatingArea )) {
			return false;
		}
		
		if (locatingZone == null ? _cast.locatingZone != locatingZone : !locatingZone.equals( _cast.locatingZone )) {
			return false;
		}
		
		if (locationBay == null ? _cast.locationBay != locationBay : !locationBay.equals( _cast.locationBay )) {
			return false;
		}
		
		if (locationLevel == null ? _cast.locationLevel != locationLevel : !locationLevel.equals( _cast.locationLevel )) {
			return false;
		}
		
		if (locationPosition == null ? _cast.locationPosition != locationPosition : !locationPosition.equals( _cast.locationPosition )) {
			return false;
		}
		
		if (allocationZone == null ? _cast.allocationZone != allocationZone : !allocationZone.equals( _cast.allocationZone )) {
			return false;
		}
		
		if (workZone == null ? _cast.workZone != workZone : !workZone.equals( _cast.workZone )) {
			return false;
		}
		
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
			return false;
		}
		
		if (isDelete == null ? _cast.isDelete != isDelete : !isDelete.equals( _cast.isDelete )) {
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
		
		if (locationId != null) {
			_hashCode = 29 * _hashCode + locationId.hashCode();
		}
		
		if (locationCode != null) {
			_hashCode = 29 * _hashCode + locationCode.hashCode();
		}
		
		if (locationName != null) {
			_hashCode = 29 * _hashCode + locationName.hashCode();
		}
		
		if (locationType != null) {
			_hashCode = 29 * _hashCode + locationType.hashCode();
		}
		
		_hashCode = 29 * _hashCode + minProduct;
		_hashCode = 29 * _hashCode + (minProductNull ? 1 : 0);
		_hashCode = 29 * _hashCode + maxProduct;
		_hashCode = 29 * _hashCode + (maxProductNull ? 1 : 0);
		if (locatingArea != null) {
			_hashCode = 29 * _hashCode + locatingArea.hashCode();
		}
		
		if (locatingZone != null) {
			_hashCode = 29 * _hashCode + locatingZone.hashCode();
		}
		
		if (locationBay != null) {
			_hashCode = 29 * _hashCode + locationBay.hashCode();
		}
		
		if (locationLevel != null) {
			_hashCode = 29 * _hashCode + locationLevel.hashCode();
		}
		
		if (locationPosition != null) {
			_hashCode = 29 * _hashCode + locationPosition.hashCode();
		}
		
		if (allocationZone != null) {
			_hashCode = 29 * _hashCode + allocationZone.hashCode();
		}
		
		if (workZone != null) {
			_hashCode = 29 * _hashCode + workZone.hashCode();
		}
		
		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}
		
		if (isDelete != null) {
			_hashCode = 29 * _hashCode + isDelete.hashCode();
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
	 * @return WhLocationPk
	 */
	public WhLocationPk createPk()
	{
		return new WhLocationPk(locationId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.WhLocation: " );
		ret.append( "id=" + id );
		ret.append( ", locationId=" + locationId );
		ret.append( ", locationCode=" + locationCode );
		ret.append( ", locationName=" + locationName );
		ret.append( ", locationType=" + locationType );
		ret.append( ", minProduct=" + minProduct );
		ret.append( ", maxProduct=" + maxProduct );
		ret.append( ", locatingArea=" + locatingArea );
		ret.append( ", locatingZone=" + locatingZone );
		ret.append( ", locationBay=" + locationBay );
		ret.append( ", locationLevel=" + locationLevel );
		ret.append( ", locationPosition=" + locationPosition );
		ret.append( ", allocationZone=" + allocationZone );
		ret.append( ", workZone=" + workZone );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", userId=" + userId );
		ret.append( ", corpId=" + corpId );
		ret.append( ", whCode=" + whCode );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		ret.append( ", productCode=" + productCode );
		ret.append( ", productName=" + productName );
		ret.append( ", unitCode=" + unitCode );
		ret.append( ", whLocationDetail=" + whLocationDetail );
		return ret.toString();
	}

}
