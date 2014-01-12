package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class WarehouseLocation implements Serializable {
	
	protected String locationCode;
	protected String locationName;
	protected String locationType;
	protected int minProduct;
	protected int maxProduct;
	protected String locating_area;
	protected String locating_zone;
	protected String location_bay;
	protected String location_level;
	protected String location_position;
	protected String allocation_zone;
	protected String work_zone;
	protected String isActive;
	protected String isDelete;
	protected String createdBy;
	protected Date createdDate;
	protected String updatedBy;
	protected Date updatedDate;
	protected String corpId;
	protected String whCode;
	protected WhLocationDetail whLocationDetail;
	protected Product product;
	
	public WarehouseLocation(){
		
	}

	public WhLocationDetail getWhLocationDetail() {
		return whLocationDetail;
	}

	public void setWhLocationDetail(WhLocationDetail whLocationDetail) {
		this.whLocationDetail = whLocationDetail;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public String getLocating_area() {
		return locating_area;
	}

	public void setLocating_area(String locating_area) {
		this.locating_area = locating_area;
	}

	public String getLocating_zone() {
		return locating_zone;
	}

	public void setLocating_zone(String locating_zone) {
		this.locating_zone = locating_zone;
	}

	public String getLocation_bay() {
		return location_bay;
	}

	public void setLocation_bay(String location_bay) {
		this.location_bay = location_bay;
	}

	public String getLocation_level() {
		return location_level;
	}

	public void setLocation_level(String location_level) {
		this.location_level = location_level;
	}

	public String getLocation_position() {
		return location_position;
	}

	public void setLocation_position(String location_position) {
		this.location_position = location_position;
	}

	public String getAllocation_zone() {
		return allocation_zone;
	}

	public void setAllocation_zone(String allocation_zone) {
		this.allocation_zone = allocation_zone;
	}

	public String getWork_zone() {
		return work_zone;
	}

	public void setWork_zone(String work_zone) {
		this.work_zone = work_zone;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public int getMinProduct() {
		return minProduct;
	}

	public void setMinProduct(int minProduct) {
		this.minProduct = minProduct;
	}

	public int getMaxProduct() {
		return maxProduct;
	}

	public void setMaxProduct(int maxProduct) {
		this.maxProduct = maxProduct;
	}
	
	public WarehouseLocationPk createPk() {
		return new WarehouseLocationPk(locationCode);
	}

	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( "com.app.wms.engine.db.dto.WarehouseLocation: " );
		sb.append( "locationCode=" + locationCode );
		sb.append( ", locationName=" + locationName );
		sb.append( ", minProduct=" + minProduct );
		sb.append( ", maxProduct=" + maxProduct );
		sb.append( ", locating_area=" +locating_area );
		sb.append( ", locating_zone=" +locating_zone );
		sb.append( ", location_bay=" +location_bay );
		sb.append( ", location_level=" +location_level );
		sb.append( ", location_position=" +location_position );
		sb.append( ", allocation_zone=" +allocation_zone );
		sb.append( ", work_zone=" +work_zone );
		sb.append( ", isActive=" + isActive );
		sb.append( ", isDelete=" + isDelete );
		sb.append( ", createdBy=" + createdBy );
		sb.append( ", createdDate=" + createdDate );
		sb.append( ", updatedBy=" + updatedBy );
		sb.append( ", updatedDate=" + updatedDate );
		sb.append( ", corpId=" + corpId );
		sb.append( ", whCode=" + whCode );
		sb.append( ", whLocationDetail=" + whLocationDetail );
		sb.append( ", product=" + product );
		return sb.toString();
	}


	
}
