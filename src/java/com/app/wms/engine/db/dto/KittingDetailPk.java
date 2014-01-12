package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the kitting_detail table.
 */
public class KittingDetailPk implements Serializable
{
	protected String kittingNo;
	
	public KittingDetailPk(){
		
	}
	
	public String getKittingNo() {
		return kittingNo;
	}

	public void setKittingNo(String kittingNo) {
		this.kittingNo = kittingNo;
	}
	
	/**
	 * Method 'KittingDetailPk'
	 * 
	 * @param kittingNo
	 */
	public KittingDetailPk(final String kittingNo)
	{
		this.kittingNo = kittingNo;
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
		
		if (!(_other instanceof KittingDetailPk)) {
			return false;
		}
		
		final KittingDetailPk _cast = (KittingDetailPk) _other;
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
		ret.append( "com.app.wms.engine.dto.KittingDetailPk: " );
		return ret.toString();
	}

}
