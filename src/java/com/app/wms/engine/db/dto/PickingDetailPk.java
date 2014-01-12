package com.app.wms.engine.db.dto;

import java.io.Serializable;

public class PickingDetailPk implements Serializable
{


	protected String id;


	public PickingDetailPk()
	{
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Method 'PickingDetailPk'
	 * 
	 * @param id
	 */
	public PickingDetailPk(final String id)
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
		
		if (!(_other instanceof PickingDetailPk)) {
			return false;
		}
		
		final PickingDetailPk _cast = (PickingDetailPk) _other;
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
		ret.append( "com.app.wms.engine.db.dto.PickingDetailPk: " );
		ret.append( "id=" + id );
		return ret.toString();
	}





}
