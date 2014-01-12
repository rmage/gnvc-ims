package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class WarehouseLocationPk implements Serializable {

	protected String id;

	/**
	 * Method 'WarehouseLocationPk'
	 * 
	 */
	public WarehouseLocationPk()
	{
	}

	/** 
	 * Sets the value of id
	 */
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Method 'WarehouseLocationPk'
	 * 
	 * @param id
	 */
	public WarehouseLocationPk(final String id)
	{
		this.id = id;
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
		
		if (!(_other instanceof WarehouseLocationPk)) {
			return false;
		}
		
		final WarehouseLocationPk _cast = (WarehouseLocationPk) _other;
		if (id == null ? _cast.id != id : !id.equals( _cast.id )) {
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
		if (id != null) {
			_hashCode = 29 * _hashCode + id.hashCode();
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
		ret.append( "com.app.wms.engine.db.dto.WarehouseLocationPk: " );
		ret.append( "id=" + id );
		return ret.toString();
	}



}
