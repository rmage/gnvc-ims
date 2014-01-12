package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the SALES table.
 */
public class SalesPk implements Serializable
{
	protected String documentNo;

	/** 
	 * Sets the value of documentNo
	 */
	public void setDocumentNo(String documentNo)
	{
		this.documentNo = documentNo;
	}

	/** 
	 * Gets the value of documentNo
	 */
	public String getDocumentNo()
	{
		return documentNo;
	}

	/**
	 * Method 'SalesPk'
	 * 
	 */
	public SalesPk()
	{
	}

	/**
	 * Method 'SalesPk'
	 * 
	 * @param documentNo
	 */
	public SalesPk(final String documentNo)
	{
		this.documentNo = documentNo;
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
		
		if (!(_other instanceof SalesPk)) {
			return false;
		}
		
		final SalesPk _cast = (SalesPk) _other;
		if (documentNo == null ? _cast.documentNo != documentNo : !documentNo.equals( _cast.documentNo )) {
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
		if (documentNo != null) {
			_hashCode = 29 * _hashCode + documentNo.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.SalesPk: " );
		ret.append( "documentNo=" + documentNo );
		return ret.toString();
	}

}
