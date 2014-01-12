package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.util.Date;

public class Corporate implements Serializable {
	
	protected String id;
	protected String name;
	protected String address1;
	protected String address2;
	protected String address3;
	protected String zipcode;
	protected String phone1;
	protected String phone2;
	protected String fax;
	protected String province;
	protected String isActive;
	protected String isDeleted;
	protected int createdBy;
	protected Date createdDate;
	protected int updatedBy;
	protected Date updatedDate;
	protected City city;
	protected Wh wh;
	
	public Corporate (){}


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


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}


	public int getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public int getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}


	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Wh getWh() {
		return wh;
	}

	public void setWh(Wh wh) {
		this.wh = wh;
	}
	
	/**
	 * Method 'createPk'
	 * 
	 * @return CorporatePk
	 */
	public CorporatePk createPk()
	{
		return new CorporatePk(id);
	}
	
	public String toString ()
	{
		StringBuffer sb = new StringBuffer();
		sb.append( "com.app.wms.engine.db.dto.Corporate: " );
		sb.append( "  id=" + id );
		sb.append( ", name=" + name );
		sb.append( ", address1=" + address1 );
		sb.append( ", address2=" + address2);
		sb.append( ", address3=" + address3);
		sb.append( ", zipcode=" + zipcode);
		sb.append( ", phone1=" + phone1);
		sb.append( ", phone2=" + phone2);
		sb.append( ", fax=" + fax);
		sb.append( ", province=" + province);
		sb.append( ", isActive=" + isActive);
		sb.append( ", isDeleted=" + isDeleted);
		sb.append( ", createdBy=" + createdBy);
		sb.append( ", createdDate=" + createdDate);
		sb.append( ", updatedBy=" + updatedBy);
		sb.append( ", updatedDate=" + updatedDate);
		sb.append( ", City=" + city);
		sb.append( ", Wh=" + wh);
		return sb.toString();
	}

}
