package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the CITY table.
 */
public class RegionPk implements Serializable
{
	protected String regionCode;

	/** 
	 * Sets the value of regionCode
	 */
	public void setRegionCode(String regionCode)
	{
		this.regionCode = regionCode;
	}

	/** 
	 * Gets the value of regionCode
	 */
	public String getRegionCode()
	{
		return regionCode;
	}

	/**
	 * Method 'CityPk'
	 * 
	 */
	public RegionPk()
	{
	}

	/**
	 * Method 'CityPk'
	 * 
	 * @param regionCode
	 */
	public RegionPk(final String regionCode)
	{
		this.regionCode = regionCode;
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
		
		if (!(_other instanceof RegionPk)) {
			return false;
		}
		
		final RegionPk _cast = (RegionPk) _other;
		if (regionCode == null ? _cast.regionCode != regionCode : !regionCode.equals( _cast.regionCode )) {
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
		if (regionCode != null) {
			_hashCode = 29 * _hashCode + regionCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.CityPk: " );
		ret.append( "regionCode=" + regionCode );
		return ret.toString();
	}

}
