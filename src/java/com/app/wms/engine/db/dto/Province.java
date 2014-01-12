package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Province implements Serializable {
	
	protected String provinceCode;
	protected String name;
	protected String isActive;
	protected String isDelete;
	protected String createdBy;
	protected Date createdDate;
	protected String updatedBy;
	protected Date updatedDate;
	
	public Province (){
		
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	

	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("com.app.wms.engine.db.dto.Province: ");
		sb.append("provinceCode= " +provinceCode);
		sb.append(", name=" +name);
		sb.append(", isActive=" +isActive);
		sb.append(", isDelete=" +isDelete);
		sb.append(", createdBy=" +createdBy);
		sb.append(", createdDate=" +createdDate);
		sb.append(", updatedBy=" +updatedBy);
		sb.append(", updatedDate=" +updatedDate);
		
		return sb.toString();
	}
	


}
