package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the PRICE_UPLOAD table.
 */
public class PriceUploadPk implements Serializable
{
	protected BigDecimal uploadId;

	/** 
	 * Sets the value of uploadId
	 */
	public void setUploadId(BigDecimal uploadId)
	{
		this.uploadId = uploadId;
	}

	/** 
	 * Gets the value of uploadId
	 */
	public BigDecimal getUploadId()
	{
		return uploadId;
	}

	/**
	 * Method 'PriceUploadPk'
	 * 
	 */
	public PriceUploadPk()
	{
	}

	/**
	 * Method 'PriceUploadPk'
	 * 
	 * @param uploadId
	 */
	public PriceUploadPk(final BigDecimal uploadId)
	{
		this.uploadId = uploadId;
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
		
		if (!(_other instanceof PriceUploadPk)) {
			return false;
		}
		
		final PriceUploadPk _cast = (PriceUploadPk) _other;
		if (uploadId == null ? _cast.uploadId != uploadId : !uploadId.equals( _cast.uploadId )) {
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
		if (uploadId != null) {
			_hashCode = 29 * _hashCode + uploadId.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.PriceUploadPk: " );
		ret.append( "uploadId=" + uploadId );
		return ret.toString();
	}

}
