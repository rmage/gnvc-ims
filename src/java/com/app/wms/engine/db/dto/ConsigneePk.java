package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the consignee table.
 */
public class ConsigneePk implements Serializable
{
	protected String consigneeCode;

	/** 
	 * This attribute represents whether the primitive attribute id is null.
	 */
	protected boolean consigneeCodeNull;

	
	/**
	 * Method 'ConsigneePk'
	 * 
	 */
	public ConsigneePk()
	{
	}

	public String getConsigneeCode() {
		return consigneeCode;
	}

	public void setConsigneeCode(String consigneeCode) {
		this.consigneeCode = consigneeCode;
	}

	/**
	 * Method 'ConsigneePk'
	 * 
	 * @param id
	 */
	public ConsigneePk(final String consigneeCode)
	{
		this.consigneeCode = consigneeCode;
	}

	/** 
	 * Sets the value of consigneeCodeNull
	 */
	public void setConsigneeNull(boolean consigneeCodeNull)
	{
		this.consigneeCodeNull = consigneeCodeNull;
	}

	/** 
	 * Gets the value of consigneeCodeNull
	 */
	public boolean isConsigneeNull()
	{
		return consigneeCodeNull;
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
		
		if (!(_other instanceof ConsigneePk)) {
			return false;
		}
		
		final ConsigneePk _cast = (ConsigneePk) _other;
		if (consigneeCode != _cast.consigneeCode) {
			return false;
		}
		
		if (consigneeCodeNull != _cast.consigneeCodeNull) {
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
		ret.append( "com.app.wms.engine.db.dto.ConsigneePk: " );
		ret.append( "consigneeCode=" + consigneeCode );
		return ret.toString();
	}

}
