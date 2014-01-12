package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class CrossDockPk implements Serializable 
{

	protected String CrossDockCode;

	/** 
	 * Sets the value of CrossDockCode
	 */
	public void setCrossDockCode(String CrossDockCode)
	{
		this.CrossDockCode = CrossDockCode;
	}

	/** 
	 * Gets the value of CrossDockCode
	 */
	public String getCrossDockCode()
	{
		return CrossDockCode;
	}

	/**
	 * Method 'CrossDockPk'
	 * 
	 */
	public CrossDockPk()
	{
	}

	/**
	 * Method 'CrossDockPk'
	 * 
	 * @param CrossDockCode
	 */
	public CrossDockPk(final String CrossDockCode)
	{
		this.CrossDockCode = CrossDockCode;
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
		
		if (!(_other instanceof CrossDockPk)) {
			return false;
		}
		
		final CrossDockPk _cast = (CrossDockPk) _other;
		if (CrossDockCode == null ? _cast.CrossDockCode != CrossDockCode : !CrossDockCode.equals( _cast.CrossDockCode )) {
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
		if (CrossDockCode != null) {
			_hashCode = 29 * _hashCode + CrossDockCode.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.CrossDockPk: " );
		ret.append( "CrossDockCode=" + CrossDockCode );
		return ret.toString();
	}



}
