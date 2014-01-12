package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the packing_detail table.
 */
public class PackingDetailPk implements Serializable
{
	protected String packingNo;
	
	public PackingDetailPk(){
		
	}
	
	public String getPackingNo() {
		return packingNo;
	}

	public void setPackingNo(String packingNo) {
		this.packingNo = packingNo;
	}
	
	/**
	 * Method 'PackingDetailPk'
	 * 
	 * @param packingNo
	 */
	public PackingDetailPk(final String packingNo)
	{
		this.packingNo = packingNo;
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
		
		if (!(_other instanceof PackingDetailPk)) {
			return false;
		}
		
		final PackingDetailPk _cast = (PackingDetailPk) _other;
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
		ret.append( "com.app.wms.engine.db.dto.PackingDetailPk: " );
		return ret.toString();
	}

}
