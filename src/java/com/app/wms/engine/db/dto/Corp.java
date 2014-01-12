package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.util.Date;

public class Corp implements Serializable
{
	protected int id;
	/** 
	 * This attribute maps to the column corp_id in the corp table.
	 */
	protected String corpId;
	
	/** 
	 * This attribute maps to the column corp_name in the corp table.
	 */
	protected String corpName;

	/** 
	 * This attribute maps to the column wh_code in the corp table.
	 */
	protected String whCode;

	/** 
	 * This attribute maps to the column address1 in the corp table.
	 */
	protected String address1;

	/** 
	 * This attribute maps to the column address2 in the corp table.
	 */
	protected String address2;

	/** 
	 * This attribute maps to the column address3 in the corp table.
	 */
	protected String address3;

	/** 
	 * This attribute maps to the column email in the corp table.
	 */
	protected String email;

	/** 
	 * This attribute maps to the column city_code in the corp table.
	 */
	protected String cityCode;

	/** 
	 * This attribute maps to the column zipcode in the corp table.
	 */
	protected String zipcode;

	/** 
	 * This attribute maps to the column phone1 in the corp table.
	 */
	protected String phone1;

	/** 
	 * This attribute maps to the column phone2 in the corp table.
	 */
	protected String phone2;

	/** 
	 * This attribute maps to the column fax in the corp table.
	 */
	protected String fax;

	/** 
	 * This attribute maps to the column province_code in the corp table.
	 */
	protected String provinceCode;

	/** 
	 * This attribute maps to the column is_active in the corp table.
	 */
	protected String isActive;

	/** 
	 * This attribute maps to the column is_delete in the corp table.
	 */
	protected String isDelete;

	/** 
	 * This attribute maps to the column created_by in the corp table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column created_date in the corp table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column updated_by in the corp table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column updated_date in the corp table.
	 */
	protected Date updatedDate;

	/**
	 * Method 'Corp'
	 * 
	 */
	public Corp()
	{
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	 * Method 'getCorpName'
	 * 
	 * @return String
	 */
	public String getCorpName()
	{
		return corpName;
	}

	/**
	 * Method 'setCorpName'
	 * 
	 * @param corpName
	 */
	public void setCorpName(String corpName)
	{
		this.corpName = corpName;
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
	 * Method 'getAddress1'
	 * 
	 * @return String
	 */
	public String getAddress1()
	{
		return address1;
	}

	/**
	 * Method 'setAddress1'
	 * 
	 * @param address1
	 */
	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	/**
	 * Method 'getAddress2'
	 * 
	 * @return String
	 */
	public String getAddress2()
	{
		return address2;
	}

	/**
	 * Method 'setAddress2'
	 * 
	 * @param address2
	 */
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	/**
	 * Method 'getAddress3'
	 * 
	 * @return String
	 */
	public String getAddress3()
	{
		return address3;
	}

	/**
	 * Method 'setAddress3'
	 * 
	 * @param address3
	 */
	public void setAddress3(String address3)
	{
		this.address3 = address3;
	}

	/**
	 * Method 'getEmail'
	 * 
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Method 'setEmail'
	 * 
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Method 'getCityCode'
	 * 
	 * @return String
	 */
	public String getCityCode()
	{
		return cityCode;
	}

	/**
	 * Method 'setCityCode'
	 * 
	 * @param cityCode
	 */
	public void setCityCode(String cityCode)
	{
		this.cityCode = cityCode;
	}

	/**
	 * Method 'getZipcode'
	 * 
	 * @return String
	 */
	public String getZipcode()
	{
		return zipcode;
	}

	/**
	 * Method 'setZipcode'
	 * 
	 * @param zipcode
	 */
	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	/**
	 * Method 'getPhone1'
	 * 
	 * @return String
	 */
	public String getPhone1()
	{
		return phone1;
	}

	/**
	 * Method 'setPhone1'
	 * 
	 * @param phone1
	 */
	public void setPhone1(String phone1)
	{
		this.phone1 = phone1;
	}

	/**
	 * Method 'getPhone2'
	 * 
	 * @return String
	 */
	public String getPhone2()
	{
		return phone2;
	}

	/**
	 * Method 'setPhone2'
	 * 
	 * @param phone2
	 */
	public void setPhone2(String phone2)
	{
		this.phone2 = phone2;
	}

	/**
	 * Method 'getFax'
	 * 
	 * @return String
	 */
	public String getFax()
	{
		return fax;
	}

	/**
	 * Method 'setFax'
	 * 
	 * @param fax
	 */
	public void setFax(String fax)
	{
		this.fax = fax;
	}

	/**
	 * Method 'getProvinceCode'
	 * 
	 * @return String
	 */
	public String getProvinceCode()
	{
		return provinceCode;
	}

	/**
	 * Method 'setProvinceCode'
	 * 
	 * @param provinceCode
	 */
	public void setProvinceCode(String provinceCode)
	{
		this.provinceCode = provinceCode;
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
		
		if (!(_other instanceof Corporate)) {
			return false;
		}
		
		final Corp _cast = (Corp) _other;
		if (corpId == null ? _cast.corpId != corpId : !corpId.equals( _cast.corpId )) {
			return false;
		}
		
		if (corpName == null ? _cast.corpName != corpName : !corpName.equals( _cast.corpName )) {
			return false;
		}
		
//		if (whCode == null ? _cast.whCode != whCode : !whCode.equals( _cast.whCode )) {
//			return false;
//		}
		
		if (address1 == null ? _cast.address1 != address1 : !address1.equals( _cast.address1 )) {
			return false;
		}
		
		if (address2 == null ? _cast.address2 != address2 : !address2.equals( _cast.address2 )) {
			return false;
		}
		
		if (address3 == null ? _cast.address3 != address3 : !address3.equals( _cast.address3 )) {
			return false;
		}
		
		if (email == null ? _cast.email != email : !email.equals( _cast.email )) {
			return false;
		}
		
		if (cityCode == null ? _cast.cityCode != cityCode : !cityCode.equals( _cast.cityCode )) {
			return false;
		}
		
		if (zipcode == null ? _cast.zipcode != zipcode : !zipcode.equals( _cast.zipcode )) {
			return false;
		}
		
		if (phone1 == null ? _cast.phone1 != phone1 : !phone1.equals( _cast.phone1 )) {
			return false;
		}
		
		if (phone2 == null ? _cast.phone2 != phone2 : !phone2.equals( _cast.phone2 )) {
			return false;
		}
		
		if (fax == null ? _cast.fax != fax : !fax.equals( _cast.fax )) {
			return false;
		}
		
		if (provinceCode == null ? _cast.provinceCode != provinceCode : !provinceCode.equals( _cast.provinceCode )) {
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
		
		if (corpId != null) {
			_hashCode = 29 * _hashCode + corpId.hashCode();
		}
		
		if (corpName != null) {
			_hashCode = 29 * _hashCode + corpName.hashCode();
		}
		
//		if (whCode != null) {
//			_hashCode = 29 * _hashCode + whCode.hashCode();
//		}
		
		if (address1 != null) {
			_hashCode = 29 * _hashCode + address1.hashCode();
		}
		
		if (address2 != null) {
			_hashCode = 29 * _hashCode + address2.hashCode();
		}
		
		if (address3 != null) {
			_hashCode = 29 * _hashCode + address3.hashCode();
		}
		
		if (email != null) {
			_hashCode = 29 * _hashCode + email.hashCode();
		}
		
		if (cityCode != null) {
			_hashCode = 29 * _hashCode + cityCode.hashCode();
		}
		
		if (zipcode != null) {
			_hashCode = 29 * _hashCode + zipcode.hashCode();
		}
		
		if (phone1 != null) {
			_hashCode = 29 * _hashCode + phone1.hashCode();
		}
		
		if (phone2 != null) {
			_hashCode = 29 * _hashCode + phone2.hashCode();
		}
		
		if (fax != null) {
			_hashCode = 29 * _hashCode + fax.hashCode();
		}
		
		if (provinceCode != null) {
			_hashCode = 29 * _hashCode + provinceCode.hashCode();
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
	 * @return CorpPk
	 */
	public CorpPk createPk()
	{
		return new CorpPk(corpId);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.Corp: " );
		ret.append( "id=" + id );
		ret.append( "corpId=" + corpId );
		ret.append( ", corpName=" + corpName );
//		ret.append( ", whCode=" + whCode );
		ret.append( ", address1=" + address1 );
		ret.append( ", address2=" + address2 );
		ret.append( ", address3=" + address3 );
		ret.append( ", email=" + email );
		ret.append( ", cityCode=" + cityCode );
		ret.append( ", zipcode=" + zipcode );
		ret.append( ", phone1=" + phone1 );
		ret.append( ", phone2=" + phone2 );
		ret.append( ", fax=" + fax );
		ret.append( ", provinceCode=" + provinceCode );
		ret.append( ", isActive=" + isActive );
		ret.append( ", isDelete=" + isDelete );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
		return ret.toString();
	}

}
