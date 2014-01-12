package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the wh_location_detail table.
 */
public class WhLocationDetailPk implements Serializable
{
	protected String locationId;

	/** 
	 * This attribute represents whether the primitive attribute id is null.
	 */
	protected boolean locationIdNull;

	
	/**
	 * Method 'WhLocationDetailPk'
	 * 
	 */
	public WhLocationDetailPk()
	{
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * Method 'WhLocationDetailPk'
	 * 
	 * @param locationId
	 */
	public WhLocationDetailPk(final String locationId)
	{
		this.locationId = locationId;
	}

	/** 
	 * Sets the value of locationIdNull
	 */
	public void setIdNull(boolean locationIdNull)
	{
		this.locationIdNull = locationIdNull;
	}

	/** 
	 * Gets the value of idNull
	 */
	public boolean islocationIdNull()
	{
		return locationIdNull;
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
		
		if (!(_other instanceof WhLocationDetailPk)) {
			return false;
		}
		
		final WhLocationDetailPk _cast = (WhLocationDetailPk) _other;
		if (locationId != _cast.locationId) {
			return false;
		}
		
		if (locationIdNull != _cast.locationIdNull) {
			return false;
		}
		
		return true;
	}

	

	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.WhLocationDetailPk: " );
		ret.append( "locationId=" + locationId );
		return ret.toString();
	}

}
