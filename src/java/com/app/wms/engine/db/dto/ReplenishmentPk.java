package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the replenishment table.
 */
public class ReplenishmentPk implements Serializable
{
	protected String replenishNo;

	/** 
	 * Sets the value of replenishNo
	 */
	public void setReplenishNo(String replenishNo)
	{
		this.replenishNo = replenishNo;
	}

	/** 
	 * Gets the value of replenishNo
	 */
	public String getReplenishNo()
	{
		return replenishNo;
	}

	/**
	 * Method 'ReplenishmentPk'
	 * 
	 */
	public ReplenishmentPk()
	{
	}

	/**
	 * Method 'ReplenishmentPk'
	 * 
	 * @param replenishNo
	 */
	public ReplenishmentPk(final String replenishNo)
	{
		this.replenishNo = replenishNo;
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
		
		if (!(_other instanceof ReplenishmentPk)) {
			return false;
		}
		
		final ReplenishmentPk _cast = (ReplenishmentPk) _other;
		if (replenishNo == null ? _cast.replenishNo != replenishNo : !replenishNo.equals( _cast.replenishNo )) {
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
		if (replenishNo != null) {
			_hashCode = 29 * _hashCode + replenishNo.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.ReplenishmentPk: " );
		ret.append( "replenishNo=" + replenishNo );
		return ret.toString();
	}

}
