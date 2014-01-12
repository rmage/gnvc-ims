package com.app.wms.engine.db.dto;

import com.app.wms.engine.db.dao.*;
import com.app.wms.engine.db.factory.*;
import com.app.wms.engine.db.exceptions.*;
import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import java.util.Date;

public class City implements Serializable
{
	/** 
	 * This attribute maps to the column CITY_CODE in the CITY table.
	 */
	protected String cityCode;

	/** 
	 * This attribute maps to the column NAME in the CITY table.
	 */
	protected String name;

	/** 
	 * This attribute maps to the column CREATED_BY in the CITY table.
	 */
	protected String createdBy;

	/** 
	 * This attribute maps to the column CREATED_DATE in the CITY table.
	 */
	protected Date createdDate;

	/** 
	 * This attribute maps to the column UPDATED_BY in the CITY table.
	 */
	protected String updatedBy;

	/** 
	 * This attribute maps to the column UPDATED_DATE in the CITY table.
	 */
	protected Date updatedDate;

        /**
	 * This attribute maps to the column IS_ACTIVE in the BRANCH table.
	 */
	protected String isActive;

	/**
	 * Method 'City'
	 * 
	 */
	public City()
	{
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
		
		if (!(_other instanceof City)) {
			return false;
		}
		
		final City _cast = (City) _other;
		if (cityCode == null ? _cast.cityCode != cityCode : !cityCode.equals( _cast.cityCode )) {
			return false;
		}
		
		if (name == null ? _cast.name != name : !name.equals( _cast.name )) {
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
		if (isActive == null ? _cast.isActive != isActive : !isActive.equals( _cast.isActive )) {
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
		if (cityCode != null) {
			_hashCode = 29 * _hashCode + cityCode.hashCode();
		}
		
		if (name != null) {
			_hashCode = 29 * _hashCode + name.hashCode();
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

		if (isActive != null) {
			_hashCode = 29 * _hashCode + isActive.hashCode();
		}

		return _hashCode;
	}

	/**
	 * Method 'createPk'
	 * 
	 * @return CityPk
	 */
	public CityPk createPk()
	{
		return new CityPk(cityCode);
	}

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.City: " );
		ret.append( "cityCode=" + cityCode );
		ret.append( ", name=" + name );
		ret.append( ", createdBy=" + createdBy );
		ret.append( ", createdDate=" + createdDate );
		ret.append( ", updatedBy=" + updatedBy );
		ret.append( ", updatedDate=" + updatedDate );
                ret.append( ", isActive=" + isActive );
		return ret.toString();
	}

}
