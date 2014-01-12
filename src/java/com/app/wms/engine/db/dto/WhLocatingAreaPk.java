package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the wh_locating_area table.
 */
public class WhLocatingAreaPk implements Serializable
{
	//protected int id;
	
	protected String locatingId;

	/** 
	 * This attribute represents whether the primitive attribute id is null.
	 */
	protected boolean idNull;

	/**
	 * Method 'WhLocatingAreaPk'
	 * 
	 */
	public WhLocatingAreaPk()
	{
	}

	public String getLocatingId() {
		return locatingId;
	}

	public void setLocatingId(String locatingId) {
		this.locatingId = locatingId;
	}

	/**
	 * Method 'WhLocatingAreaPk'
	 * 
	 * @param locatingId
	 */
	public WhLocatingAreaPk(final String locatingId)
	{
		this.locatingId = locatingId;
	}

	/** 
	 * Sets the value of idNull
	 */
	public void setIdNull(boolean idNull)
	{
		this.idNull = idNull;
	}

	/** 
	 * Gets the value of idNull
	 */
	public boolean isIdNull()
	{
		return idNull;
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
		
		if (!(_other instanceof WhLocatingAreaPk)) {
			return false;
		}
		
		final WhLocatingAreaPk _cast = (WhLocatingAreaPk) _other;
//		if (id != _cast.id) {
//			return false;
//		}
		
		if (idNull != _cast.idNull) {
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
		ret.append( "com.app.wms.engine.db.dto.WhLocatingAreaPk: " );
		ret.append( "locatingId=" + locatingId );
		return ret.toString();
	}

}
