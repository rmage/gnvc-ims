package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the CITY table.
 */
public class CityPk implements Serializable
{
	protected String cityCode;

	/** 
	 * Sets the value of cityCode
	 */
	public void setCityCode(String cityCode)
	{
		this.cityCode = cityCode;
	}

	/** 
	 * Gets the value of cityCode
	 */
	public String getCityCode()
	{
		return cityCode;
	}

	/**
	 * Method 'CityPk'
	 * 
	 */
	public CityPk()
	{
	}

	/**
	 * Method 'CityPk'
	 * 
	 * @param cityCode
	 */
	public CityPk(final String cityCode)
	{
		this.cityCode = cityCode;
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
		
		if (!(_other instanceof CityPk)) {
			return false;
		}
		
		final CityPk _cast = (CityPk) _other;
		if (cityCode == null ? _cast.cityCode != cityCode : !cityCode.equals( _cast.cityCode )) {
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
		ret.append( "cityCode=" + cityCode );
		return ret.toString();
	}

}
