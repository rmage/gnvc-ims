package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class SalesOrderPk implements Serializable

{


	protected String so_number;


	public SalesOrderPk()
	{
	}

	public String getSo_number() {
		return so_number;
	}

	public void setSo_number(String so_number) {
		this.so_number = so_number;
	}

	/**
	 * Method 'SalesOrderPk'
	 * 
	 * @param so_number
	 */
	public SalesOrderPk(final String so_number)
	{
		this.so_number = so_number;
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
		
		if (!(_other instanceof SalesOrderPk)) {
			return false;
		}
		
		final SalesOrderPk _cast = (SalesOrderPk) _other;
		if (so_number == null ? _cast.so_number != so_number : !so_number.equals( _cast.so_number )) {
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
		if (so_number != null) {
			_hashCode = 29 * _hashCode + so_number.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.SalesOrderPk: " );
		ret.append( "so_number=" + so_number );
		return ret.toString();
	}





}
