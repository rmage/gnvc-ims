package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the deliveryorder table.
 */
public class DeliveryPk implements Serializable
{
	protected String deliveryNo;

	/** 
	 * Sets the value of deliveryNo
	 */
	public void setDeliveryNo(String deliveryNo)
	{
		this.deliveryNo = deliveryNo;
	}

	/** 
	 * Gets the value of deliveryNo
	 */
	public String getDeliveryNo()
	{
		return deliveryNo;
	}

	/**
	 * Method 'DeliveryorderPk'
	 * 
	 */
	public DeliveryPk()
	{
	}

	/**
	 * Method 'DeliveryorderPk'
	 * 
	 * @param deliveryNo
	 */
	public DeliveryPk(final String deliveryNo)
	{
		this.deliveryNo = deliveryNo;
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
		
		if (!(_other instanceof DeliveryPk)) {
			return false;
		}
		
		final DeliveryPk _cast = (DeliveryPk) _other;
		if (deliveryNo == null ? _cast.deliveryNo != deliveryNo : !deliveryNo.equals( _cast.deliveryNo )) {
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
		if (deliveryNo != null) {
			_hashCode = 29 * _hashCode + deliveryNo.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.DeliveryorderPk: " );
		ret.append( "deliveryNo=" + deliveryNo );
		return ret.toString();
	}

}
