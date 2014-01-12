package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * This class represents the primary key of the BRANCH table.
 */
public class BranchPk implements Serializable
{
	protected String branchCode;

	/** 
	 * Sets the value of branchCode
	 */
	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}

	/** 
	 * Gets the value of branchCode
	 */
	public String getBranchCode()
	{
		return branchCode;
	}

	/**
	 * Method 'BranchPk'
	 * 
	 */
	public BranchPk()
	{
	}

	/**
	 * Method 'BranchPk'
	 * 
	 * @param branchCode
	 */
	public BranchPk(final String branchCode)
	{
		this.branchCode = branchCode;
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
		
		if (!(_other instanceof BranchPk)) {
			return false;
		}
		
		final BranchPk _cast = (BranchPk) _other;
		if (branchCode == null ? _cast.branchCode != branchCode : !branchCode.equals( _cast.branchCode )) {
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
		if (branchCode != null) {
			_hashCode = 29 * _hashCode + branchCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.BranchPk: " );
		ret.append( "branchCode=" + branchCode );
		return ret.toString();
	}

}
