package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class SalesOrderDetailPk implements Serializable
{

	protected String so_number;


	public SalesOrderDetailPk()
	{
	}

	public String getSo_number() {
		return so_number;
	}

	public void setSo_number(String so_number) {
		this.so_number = so_number;
	}

	/**
	 * Method 'SalesOrderDetailPk'
	 * 
	 * @param so_number
	 */
	public SalesOrderDetailPk(final String so_number)
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
		
		if (!(_other instanceof SalesOrderDetailPk)) {
			return false;
		}
		
		final SalesOrderDetailPk _cast = (SalesOrderDetailPk) _other;
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
		ret.append( "com.app.wms.engine.db.dto.SalesOrderDetailPk: " );
		ret.append( "so_number=" + so_number );
		return ret.toString();
	}







}
