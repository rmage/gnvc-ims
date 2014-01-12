package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class VolumeProduct implements Serializable {
	
	protected String id;
	protected String name;
	protected String weight;
	protected String length;
	protected String width;
	protected String height;
	protected String isActive;
	protected String isDelete;
	protected String createdBy;
	protected Date createdDate;
	protected String updatedBy;
	protected Date updatedDate;
	
	public VolumeProduct(){
		
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( "com.app.wms.engine.db.dto.VolumeProduct: " );
		sb.append( "id=" + id );
		sb.append( ", name=" + name );
		sb.append( ", weight=" + weight );
		sb.append( ", length=" + length );
		sb.append( ", width=" + width );
		sb.append( ", height=" + height );
		sb.append( ", isActive=" + isActive );
		sb.append( ", isDelete=" + isDelete );
		sb.append( ", createdBy=" + createdBy );
		sb.append( ", createdDate=" + createdDate );
		sb.append( ", updatedBy=" + updatedBy );
		sb.append( ", updatedDate=" + updatedDate );
		return sb.toString();
	}

}
